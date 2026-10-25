/**
 * KEAM College Navigator - Interactive Web Client
 * Developed for Java Mini Project Group 5
 */

let allRecommendations = [];
let activeStatusFilter = 'ALL';
let myOptionList = JSON.parse(localStorage.getItem('keam_option_list') || '[]');
let allCutoffsData = [];
let allCollegesData = [];

document.addEventListener('DOMContentLoaded', async () => {
    initNavigation();
    await loadInitialData();
    updateOptionCount();
    runPrediction(); // Initial prediction with defaults
});

// --- Tab Navigation ---
function initNavigation() {
    const navButtons = document.querySelectorAll('.nav-btn');
    navButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            const targetTab = btn.getAttribute('data-tab');
            switchTab(targetTab);
        });
    });
}

function switchTab(tabId) {
    document.querySelectorAll('.nav-btn').forEach(b => b.classList.remove('active'));
    document.querySelectorAll('.tab-pane').forEach(p => p.classList.remove('active'));

    const btn = document.querySelector(`[data-tab="${tabId}"]`);
    const pane = document.getElementById(tabId);
    if (btn) btn.classList.add('active');
    if (pane) pane.classList.add('active');

    if (tabId === 'cutoffsTab' && allCutoffsData.length === 0) {
        loadCutoffsTable();
    }
    if (tabId === 'optionListTab') {
        renderOptionList();
    }
}

// --- Data Fetching ---
async function loadInitialData() {
    try {
        // Load Districts
        const distRes = await fetch('/api/districts');
        const districts = await distRes.json();
        const distSelect = document.getElementById('districtSelect');
        distSelect.innerHTML = districts.map(d => `<option value="${d}" ${d === 'Ernakulam' ? 'selected' : ''}>${d}</option>`).join('');

        // Load Colleges
        const colRes = await fetch('/api/colleges');
        allCollegesData = await colRes.json();
        const colFilter = document.getElementById('cutoffCollegeFilter');
        if (colFilter) {
            allCollegesData.forEach(c => {
                const opt = document.createElement('option');
                opt.value = c.code;
                opt.textContent = `${c.code} - ${c.name}`;
                colFilter.appendChild(opt);
            });
        }

        // Load Courses
        const courseRes = await fetch('/api/courses');
        const courses = await courseRes.json();
        const courseFilter = document.getElementById('cutoffCourseFilter');
        if (courseFilter) {
            courses.forEach(c => {
                const opt = document.createElement('option');
                opt.value = c.name;
                opt.textContent = c.name;
                courseFilter.appendChild(opt);
            });
        }
    } catch (e) {
        console.error('Failed to load initial data:', e);
        showToast('⚠️ Could not connect to Java backend. Is the server running?');
    }
}

// --- Presets ---
function setRank(val) {
    document.getElementById('rankInput').value = val;
    runPrediction();
}

// --- Prediction Logic ---
async function runPrediction() {
    const rank = document.getElementById('rankInput').value;
    const category = document.getElementById('categorySelect').value;
    const district = document.getElementById('districtSelect').value;
    const course = document.getElementById('courseFilter').value;
    const nearby = document.getElementById('nearbyOnlyCheckbox').checked;
    const sort = document.getElementById('sortSelect').value;

    const checkedTypes = Array.from(document.querySelectorAll('input[name="collegeType"]:checked'))
        .map(cb => cb.value)
        .join(',');

    const predictBtn = document.getElementById('predictBtn');
    if (predictBtn) predictBtn.innerHTML = '<span>⚡ Analyzing KEAM Cutoffs...</span>';

    try {
        const queryParams = new URLSearchParams({
            rank: rank || 10000,
            category: category,
            district: district,
            courses: course,
            types: checkedTypes || 'ALL',
            nearby: nearby,
            sort: sort
        });

        const res = await fetch(`/api/predict?${queryParams.toString()}`);
        const data = await res.json();

        // Update Summary Counts
        document.getElementById('safeCount').textContent = data.safeCount;
        document.getElementById('targetCount').textContent = data.targetCount;
        document.getElementById('reachCount').textContent = data.reachCount;
        document.getElementById('totalEligible').textContent = data.totalEligible;

        allRecommendations = data.recommendations || [];
        renderRecommendations();

        showToast(`✨ Generated ${allRecommendations.length} tailored recommendations for Rank #${rank}!`);
    } catch (e) {
        console.error('Prediction request failed:', e);
        showToast('❌ Error generating recommendations.');
    } finally {
        if (predictBtn) predictBtn.innerHTML = '<span>🔍 Find My Best College Matches</span>';
    }
}

// --- Render Recommendation Cards ---
function filterStatus(status) {
    activeStatusFilter = status;
    document.querySelectorAll('#statusFilterPills .pill-btn').forEach(b => {
        b.classList.toggle('active', b.getAttribute('data-filter') === status);
    });
    renderRecommendations();
}

function renderRecommendations() {
    const container = document.getElementById('recommendationCards');
    container.innerHTML = '';

    const filtered = allRecommendations.filter(rec => {
        if (activeStatusFilter === 'ALL') return true;
        return rec.status === activeStatusFilter;
    });

    if (filtered.length === 0) {
        container.innerHTML = `
            <div class="empty-state" style="grid-column: 1 / -1;">
                <div class="empty-icon">🔎</div>
                <h3>No College Options Found in this Category</h3>
                <p>Try switching to "All" status, selecting a broader branch filter, or unchecking "Nearby Only".</p>
            </div>
        `;
        return;
    }

    filtered.forEach(rec => {
        const isAdded = myOptionList.some(item =>
            item.collegeCode === rec.college.code && item.courseName === rec.courseName
        );

        let statusClass = 'prob-safe';
        let fillClass = 'fill-safe';
        let statusText = 'Safe Choice';

        if (rec.status === 'TARGET') {
            statusClass = 'prob-target';
            fillClass = 'fill-target';
            statusText = 'Realistic Target';
        } else if (rec.status === 'REACH') {
            statusClass = 'prob-reach';
            fillClass = 'fill-reach';
            statusText = 'Aspirational Reach';
        }

        let proximityTagClass = 'tag';
        if (rec.proximityLevel === 0) proximityTagClass += ' tag-home';
        else if (rec.proximityLevel === 1) proximityTagClass += ' tag-neighbor';

        const diffDisplay = rec.rankDifference >= 0
            ? `<span class="stat-diff-positive">+${rec.rankDifference.toLocaleString()} Ranks</span>`
            : `<span class="stat-diff-negative">${rec.rankDifference.toLocaleString()} Ranks</span>`;

        const card = document.createElement('div');
        card.className = 'college-card';
        card.innerHTML = `
            <div>
                <div class="card-top">
                    <span class="college-code-badge">${escapeHtml(rec.college.code)}</span>
                    <span class="probability-badge ${statusClass}">
                        ${rec.status === 'SAFE' ? '🛡️' : (rec.status === 'TARGET' ? '🎯' : '🚀')}
                        ${rec.probabilityScore}% Chance (${statusText})
                    </span>
                </div>

                <h4 class="college-name">${collegeNameMarkup(rec.college)}</h4>
                <div class="course-title">⚡ ${escapeHtml(rec.courseName)}</div>

                <div class="meta-tags">
                    <span class="tag">🏛️ ${escapeHtml(rec.college.typeName)}</span>
                    <span class="${proximityTagClass}">📍 ${escapeHtml(rec.college.district)} (${rec.proximityStatus})</span>
                    <span class="tag">⭐ ${rec.college.rating} Rating</span>
                </div>

                <div class="cutoff-stat-box">
                    <div class="stat-item">
                        <div class="stat-label">Closing Cutoff (${rec.categoryUsed})</div>
                        <div class="stat-val">#${rec.cutoffRank.toLocaleString()}</div>
                    </div>
                    <div class="stat-item">
                        <div class="stat-label">Safety Buffer</div>
                        <div class="stat-val">${diffDisplay}</div>
                    </div>
                </div>

                <div class="prob-bar-container">
                    <div class="prob-bar-header">
                        <span>Admission Likelihood</span>
                        <strong>${rec.probabilityScore}%</strong>
                    </div>
                    <div class="prob-track">
                        <div class="prob-fill ${fillClass}" style="width: ${rec.probabilityScore}%"></div>
                    </div>
                </div>

                <div class="explanation-box">
                    ${escapeHtml(rec.explanation)}
                </div>
            </div>

            <div class="card-bottom-actions">
                <button class="btn-add-option ${isAdded ? 'added' : ''}"
                    onclick="toggleOptionItem('${escapeHtml(rec.college.code)}', '${escapeHtml(rec.college.name)}', '${escapeHtml(rec.courseName)}', ${rec.cutoffRank}, '${escapeHtml(rec.status)}', '${escapeHtml(rec.college.district)}', this)">
                    ${isAdded ? '✓ Added to My List' : '+ Add to Option List'}
                </button>
            </div>
        `;
        container.appendChild(card);
    });
}

// --- Option List Strategy Builder ---
function toggleOptionItem(code, collegeName, courseName, cutoff, status, district, btn) {
    const existingIdx = myOptionList.findIndex(i => i.collegeCode === code && i.courseName === courseName);

    if (existingIdx >= 0) {
        myOptionList.splice(existingIdx, 1);
        if (btn) {
            btn.classList.remove('added');
            btn.textContent = '+ Add to Option List';
        }
        showToast(`Removed ${code} - ${courseName} from Option List`);
    } else {
        myOptionList.push({
            collegeCode: code,
            collegeName: collegeName,
            courseName: courseName,
            cutoffRank: cutoff,
            status: status,
            district: district
        });
        if (btn) {
            btn.classList.add('added');
            btn.textContent = '✓ Added to My List';
        }
        showToast(`Added ${code} - ${courseName} to Option List!`);
    }

    localStorage.setItem('keam_option_list', JSON.stringify(myOptionList));
    updateOptionCount();
    if (document.getElementById('optionListTab').classList.contains('active')) {
        renderOptionList();
    }
}

function updateOptionCount() {
    const badge = document.getElementById('optionListCount');
    if (badge) badge.textContent = myOptionList.length;
}

function renderOptionList() {
    const emptyState = document.getElementById('optionListEmpty');
    const content = document.getElementById('optionListContent');
    const tbody = document.getElementById('optionTableBody');

    if (myOptionList.length === 0) {
        emptyState.style.display = 'block';
        content.style.display = 'none';
        return;
    }

    emptyState.style.display = 'none';
    content.style.display = 'block';
    tbody.innerHTML = '';

    myOptionList.forEach((item, idx) => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>
                <div class="priority-badge">${idx + 1}</div>
            </td>
            <td>
                <strong>${collegeNameMarkup(
                    allCollegesData.find(college => college.code === item.collegeCode) || item
                )}</strong>
                <div style="font-size: 11px; color: var(--primary);">Code: ${escapeHtml(item.collegeCode)}</div>
            </td>
            <td>${escapeHtml(item.courseName)}</td>
            <td>#${item.cutoffRank.toLocaleString()}</td>
            <td>
                <span class="probability-badge ${item.status === 'SAFE' ? 'prob-safe' : (item.status === 'TARGET' ? 'prob-target' : 'prob-reach')}">
                    ${item.status}
                </span>
            </td>
            <td>${escapeHtml(item.district)}</td>
            <td>
                <button class="reorder-btn" title="Move Up" onclick="reorderOption(${idx}, -1)" ${idx === 0 ? 'disabled' : ''}>▲</button>
                <button class="reorder-btn" title="Move Down" onclick="reorderOption(${idx}, 1)" ${idx === myOptionList.length - 1 ? 'disabled' : ''}>▼</button>
                <button class="reorder-btn remove-btn" title="Remove" onclick="removeOption(${idx})">✕</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

function reorderOption(index, direction) {
    const targetIdx = index + direction;
    if (targetIdx < 0 || targetIdx >= myOptionList.length) return;
    const temp = myOptionList[index];
    myOptionList[index] = myOptionList[targetIdx];
    myOptionList[targetIdx] = temp;
    localStorage.setItem('keam_option_list', JSON.stringify(myOptionList));
    renderOptionList();
}

function removeOption(index) {
    myOptionList.splice(index, 1);
    localStorage.setItem('keam_option_list', JSON.stringify(myOptionList));
    updateOptionCount();
    renderOptionList();
    renderRecommendations();
    showToast('Removed option.');
}

function clearOptionList() {
    if (confirm('Clear all items from your KEAM Option Entry list?')) {
        myOptionList = [];
        localStorage.removeItem('keam_option_list');
        updateOptionCount();
        renderOptionList();
        renderRecommendations();
        showToast('Option list cleared.');
    }
}

// --- Cutoff Table Explorer ---
async function loadCutoffsTable() {
    try {
        const res = await fetch('/api/cutoffs');
        allCutoffsData = await res.json();
        renderCutoffTable(allCutoffsData);
    } catch (e) {
        console.error('Failed to load cutoffs table:', e);
    }
}

function filterCutoffTable() {
    const search = document.getElementById('cutoffSearchInput').value.toLowerCase();
    const college = document.getElementById('cutoffCollegeFilter').value;
    const course = document.getElementById('cutoffCourseFilter').value;

    const filtered = allCutoffsData.filter(item => {
        if (college !== 'ALL' && item.collegeCode !== college) return false;
        if (course !== 'ALL' && item.courseName !== course) return false;
        if (search) {
            const matchCol = item.collegeCode.toLowerCase().includes(search);
            const matchCourse = item.courseName.toLowerCase().includes(search);
            if (!matchCol && !matchCourse) return false;
        }
        return true;
    });

    renderCutoffTable(filtered);
}

function renderCutoffTable(data) {
    const tbody = document.getElementById('cutoffTableBody');
    tbody.innerHTML = '';

    if (data.length === 0) {
        tbody.innerHTML = `<tr><td colspan="14" style="text-align: center; padding: 30px;">No matching records found</td></tr>`;
        return;
    }

    data.forEach(item => {
        const c = item.cutoffs || {};
        const others = item.others || {};
        const othersList = Object.entries(others).map(([k, v]) => `${k}:${v}`).join(', ');

        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td><strong>${escapeHtml(item.collegeCode)}</strong></td>
            <td><span class="tag">${escapeHtml(item.collegeType)}</span></td>
            <td>${escapeHtml(item.courseName)}</td>
            <td>${formatRank(c.SM)}</td>
            <td>${formatRank(c.EZ)}</td>
            <td>${formatRank(c.MU)}</td>
            <td>${formatRank(c.LA)}</td>
            <td>${formatRank(c.DV)}</td>
            <td>${formatRank(c.VK)}</td>
            <td>${formatRank(c.BH)}</td>
            <td>${formatRank(c.SC)}</td>
            <td>${formatRank(c.ST)}</td>
            <td>${formatRank(c.EW)}</td>
            <td><small style="color: var(--text-muted);">${othersList || '-'}</small></td>
        `;
        tbody.appendChild(tr);
    });
}

function formatRank(val) {
    if (!val) return `<span style="color: var(--text-muted);">-</span>`;
    return `<strong>#${val.toLocaleString()}</strong>`;
}

// --- Toast Notification ---
function showToast(msg) {
    const toast = document.getElementById('toast');
    if (!toast) return;
    toast.textContent = msg;
    toast.classList.add('show');
    setTimeout(() => {
        toast.classList.remove('show');
    }, 3200);
}

function escapeHtml(str) {
    if (!str) return '';
    return String(str)
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;')
        .replace(/'/g, '&#039;');
}

function collegeNameMarkup(college) {
    const collegeName = String(college.name || college.collegeName || '').trim();
    const name = escapeHtml(collegeName);
    const website = String(college.website || '').trim();
    const hasWebsite = /^https?:\/\/[^"'<>]+$/i.test(website);
    const href = hasWebsite
        ? website
        : `https://www.google.com/search?q=${encodeURIComponent(`${collegeName} official website`)}`;
    const title = hasWebsite ? 'Visit college website' : 'Search for the official college website';

    return `<a href="${escapeHtml(href)}" target="_blank" rel="noopener noreferrer" title="${title}">${name}</a>`;
}
