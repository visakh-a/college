package repository;

import model.AllotmentCutoff;
import model.College;
import model.Course;

import java.util.*;

public class CollegeDatabase {
    private static final Map<String, College> COLLEGES = new LinkedHashMap<>();
    private static final Map<String, Course> COURSES = new LinkedHashMap<>();
    private static final List<AllotmentCutoff> CUTOFFS = new ArrayList<>();

    static {
        initColleges();
        initCourses();
        initCutoffs();
    }

    public static Collection<College> getAllColleges() {
        return COLLEGES.values();
    }

    public static College getCollege(String code) {
        return COLLEGES.get(code != null ? code.toUpperCase() : "");
    }

    public static Collection<Course> getAllCourses() {
        return COURSES.values();
    }

    public static List<AllotmentCutoff> getAllCutoffs() {
        return Collections.unmodifiableList(CUTOFFS);
    }

    private static void addCollege(String code, String name, String district, String type, double rating) {
        COLLEGES.put(code, new College(code, name, district, type, rating));
    }

    private static void addCollege(String code, String name, String district, String type, double rating, String website) {
        COLLEGES.put(code, new College(code, name, district, type, rating, website));
    }

    private static void addCourse(String name, String category) {
        COURSES.put(name, new Course(name, category));
    }

    private static AllotmentCutoff addCutoff(String collegeCode, String courseName, String type) {
        AllotmentCutoff cutoff = new AllotmentCutoff(collegeCode, courseName, type);
        CUTOFFS.add(cutoff);
        return cutoff;
    }

    private static void initColleges() {
        // --- Government Colleges ('G') ---
        addCollege("TVE", "College of Engineering, Thiruvananthapuram", "Thiruvananthapuram", "G", 4.9, "https://www.cet.ac.in/");
        addCollege("TCR", "Govt. Engineering College, Thrissur", "Thrissur", "G", 4.8);
        addCollege("TKM", "T K M College of Engineering, Kollam", "Kollam", "G", 4.8, "https://tkmce.ac.in/");
        addCollege("MAC", "Mar Athanasius College of Engineering, Kothamangalam", "Ernakulam", "G", 4.7, "https://mace.ac.in/");
        addCollege("NSS", "N S S College of Engineering, Palakkad", "Palakkad", "G", 4.6, "https://nssce.ac.in/");
        addCollege("KTE", "Govt. Rajiv Gandhi Institute of Tech., Kottayam", "Kottayam", "G", 4.6);
        addCollege("TRV", "Govt. Engineering College Barton Hill, Thiruvananthapuram", "Thiruvananthapuram", "G", 4.6, "https://www.gecbh.ac.in/");
        addCollege("KKE", "Government Engineering College, Kozhikkode", "Kozhikode", "G", 4.5);
        addCollege("PKD", "Government Engineering College, Palakkad", "Palakkad", "G", 4.4);
        addCollege("KNR", "Government College of Engineering, Kannur", "Kannur", "G", 4.5);
        addCollege("WYD", "Government Engineering College, Wayanad", "Wayanad", "G", 4.3);
        addCollege("IDK", "Government Engineering College, Idukki", "Idukki", "G", 4.3);
        addCollege("KCT", "Kelappaji College of Agricultural Engg & Food Tech, Tavanur", "Malappuram", "G", 4.2);
        addCollege("CDT", "Verghese Kurien Institute of Dairy & Food Tech, Thrissur", "Thrissur", "G", 4.2);
        addCollege("CDI", "College of Dairy Science & Technology, Kolahalamedu", "Idukki", "G", 4.0);
        addCollege("CDP", "College of Dairy Science and Technology, Pookode", "Wayanad", "G", 4.0);
        addCollege("CDV", "College of Dairy Science & Technology, Thiruvananthapuram", "Thiruvananthapuram", "G", 4.0);
        addCollege("COU", "KUFOS, School of Ocean Engg & Underwater Tech, Ernakulam", "Ernakulam", "G", 4.2);

        // --- Govt-Controlled / University Colleges ('N') ---
        addCollege("MDL", "Model Engineering College, Ernakulam", "Ernakulam", "N", 4.8);
        addCollege("SCT", "S C T College of Engineering, Thiruvananthapuram", "Thiruvananthapuram", "N", 4.6);
        addCollege("CHN", "College of Engineering, Chengannur", "Alappuzha", "N", 4.4);
        addCollege("LBT", "LBS Institute of Technology for Women, Thiruvananthapuram", "Thiruvananthapuram", "N", 4.3);
        addCollege("TLY", "College of Engineering, Thalassery, Kannur", "Kannur", "N", 4.2);
        addCollege("KSD", "LBS College of Engineering, Kasaragod", "Kasaragod", "N", 4.2);
        addCollege("PRN", "College of Engineering Perumon, Kollam", "Kollam", "N", 4.1);
        addCollege("CEC", "College of Engineering, Cherthala", "Alappuzha", "N", 4.1);
        addCollege("ADR", "College of Engineering, Adoor", "Pathanamthitta", "N", 4.1);
        addCollege("VDA", "College of Engineering, Vadakara", "Kozhikode", "N", 4.1);
        addCollege("UCK", "University College of Engineering, Kariavattom", "Thiruvananthapuram", "N", 4.0);
        addCollege("KGR", "College of Engineering Kidangoor, Kottayam", "Kottayam", "N", 4.0);
        addCollege("KNP", "College of Engineering, Karunagappally, Kollam", "Kollam", "N", 4.0);
        addCollege("TKR", "College of Engineering, Trikaripur", "Kasaragod", "N", 3.9);
        addCollege("UCC", "Institute of Engineering and Technology, Malappuram", "Malappuram", "N", 3.9);
        addCollege("CEA", "College of Engineering, Attingal", "Thiruvananthapuram", "N", 3.9);
        addCollege("PTA", "College of Engineering, Kallooppara, Thiruvalla", "Pathanamthitta", "N", 3.9);
        addCollege("CEM", "College of Engineering Muttathara, Thiruvananthapuram", "Thiruvananthapuram", "N", 3.8);
        addCollege("CEK", "College of Engineering, Kottarakkara, Kollam", "Kollam", "N", 3.8);
        addCollege("PRP", "CAPE College of Engineering, Punnapra, Alappuzha", "Alappuzha", "N", 3.8);
        addCollege("PJR", "College of Engineering Poonjar, Kottayam", "Kottayam", "N", 3.7);
        addCollege("PEC", "College of Engineering Pathanapuram, Kollam", "Kollam", "N", 3.7);
        addCollege("MNR", "College of Engineering, Munnar", "Idukki", "N", 3.7);
        addCollege("UCE", "University College of Engineering, Thodupuzha", "Idukki", "N", 3.7);
        addCollege("AEC", "College of Engineering of Aranmula, Pathanamthitta", "Pathanamthitta", "N", 3.6);

        // --- Private Self-Financing Colleges ('S') ---
        addCollege("RET", "Rajagiri School of Engineering and Technology, Ernakulam", "Ernakulam", "S", 4.6);
        addCollege("MUT", "Muthoot Institute of Technology and Science, Ernakulam", "Ernakulam", "S", 4.5);
        addCollege("FIT", "Federal Institute of Science and Technology, Ernakulam", "Ernakulam", "S", 4.5);
        addCollege("MGP", "Saintgits College of Engineering, Kottayam", "Kottayam", "S", 4.4);
        addCollege("MBT", "Mar Baselios College of Engineering and Tech, Thiruvananthapuram", "Thiruvananthapuram", "S", 4.4);
        addCollege("SCM", "SCMS School of Engineering and Technology, Ernakulam", "Ernakulam", "S", 4.3);
        addCollege("AJC", "Amal Jyothi College of Engineering, Kottayam", "Kottayam", "S", 4.3);
        addCollege("ASI", "Adi Shankara Institute of Engineering and Tech, Ernakulam", "Ernakulam", "S", 4.2);
        addCollege("CCE", "Christ College of Engineering, Thrissur", "Thrissur", "S", 4.2);
        addCollege("SJC", "St Josephs College of Engineering and Technology, Palai", "Kottayam", "S", 4.2);
        addCollege("VJC", "Viswajyothi College of Engineering and Tech, Ernakulam", "Ernakulam", "S", 4.1);
        addCollege("MES", "M E S College of Engineering, Kuttippuram", "Malappuram", "S", 4.1);
        addCollege("SHR", "Sahrdaya College of Engineering and Technology, Thrissur", "Thrissur", "S", 4.1);
        addCollege("TOC", "Toc H Institute of Science and Technology, Ernakulam", "Ernakulam", "S", 4.1);
        addCollege("JEC", "Jyothi Engineering College, Thrissur", "Thrissur", "S", 4.0);
        addCollege("VAS", "Vidya Academy of Science and Technology, Thrissur", "Thrissur", "S", 4.0);
        addCollege("VML", "Vimal Jyothi Engineering College, Kannur", "Kannur", "S", 4.0);
        addCollege("MCE", "Marian Engineering College, Thiruvananthapuram", "Thiruvananthapuram", "S", 4.0);
        addCollege("MBI", "Mar Baselios Institute of Technology and Science, Ernakulam", "Ernakulam", "S", 3.9);
        addCollege("MEA", "M E A Engineering College, Vengoor", "Malappuram", "S", 3.9);
        addCollege("SBC", "Sree Buddha College of Engineering, Alappuzha", "Alappuzha", "S", 3.9);
        addCollege("TKI", "T K M Institute of Technology, Kollam", "Kollam", "S", 3.8);
        addCollege("BJK", "Bishop Jerome Institute, Kollam", "Kollam", "S", 3.8);
        addCollege("RIE", "Rajadhani Institute of Engineering and Tech, Thiruvananthapuram", "Thiruvananthapuram", "S", 3.8);
        addCollege("AIK", "Albertian Institute of Science and Technology, Kochi", "Ernakulam", "S", 3.8);
        addCollege("KMC", "KMCT College of Engineering, Kozhikode", "Kozhikode", "S", 3.8);
        addCollege("SNP", "Sree Narayana Institute of Technology, Adoor", "Pathanamthitta", "S", 3.8);
        addCollege("SNG", "Sree Narayana Gurukulam College of Engineering, Ernakulam", "Ernakulam", "S", 3.8);
        addCollege("SNC", "Sree Narayana Guru College of Engineering & Tech, Kannur", "Kannur", "S", 3.7);
        addCollege("SNM", "SNM Institute of Management and Technology, Ernakulam", "Ernakulam", "S", 3.7);
        addCollege("VAK", "Vidya Academy of Science and Tech, Thiruvananthapuram", "Thiruvananthapuram", "S", 3.7);
        addCollege("KME", "K M E A Engineering College, Cochin", "Ernakulam", "S", 3.7);
        addCollege("JCE", "Jawaharlal College of Engineering and Technology, Palakkad", "Palakkad", "S", 3.6);
        addCollege("CMA", "Carmel College of Engineering and Technology, Alappuzha", "Alappuzha", "S", 3.6);
        addCollege("DMC", "M. Dasan Institute of Technology, Kozhikode", "Kozhikode", "S", 3.6);
        addCollege("EKC", "Eranad Knowledge City Technical Campus, Malappuram", "Malappuram", "S", 3.6);
        addCollege("ICE", "Ilahia College of Engineering and Technology, Ernakulam", "Ernakulam", "S", 3.6);
        addCollege("IES", "I E S College of Engineering, Thrissur", "Thrissur", "S", 3.5);
        addCollege("UKP", "UKF College of Engineering and Technology, Kollam", "Kollam", "S", 3.5);
        addCollege("MHP", "ACE College Of Engineering, Thiruvananthapuram", "Thiruvananthapuram", "S", 3.5);
        addCollege("MLM", "Mangalam College of Engineering, Kottayam", "Kottayam", "S", 3.5);
        addCollege("ATP", "Ahalia School of Engineering and Technology, Palakkad", "Palakkad", "S", 3.5);
        addCollege("AWH", "AWH Engineering College, Kozhikkode", "Kozhikode", "S", 3.5);
        addCollege("BMC", "Baselios Mathews II College of Engineering, Kollam", "Kollam", "S", 3.4);
        addCollege("CEN", "College of Engineering and Technology, Payyannur, Kannur", "Kannur", "S", 3.4);
        addCollege("KMT", "KMCT Institute of Emerging Tech & Management, Kozhikode", "Kozhikode", "S", 3.4);
        addCollege("KMY", "KMCT College of Engineering, Perinthalmanna", "Malappuram", "S", 3.4);
        addCollege("KMV", "KMCT College of Engineering, Vadakara", "Kozhikode", "S", 3.4);
        addCollege("KMZ", "KMCT College of Engineering, Kasargod", "Kasaragod", "S", 3.4);
        addCollege("KMW", "KMCT College of Engineering for Women, Kozhikode", "Kozhikode", "S", 3.4);
        addCollege("KMI", "KMCT Institute of Technology & Management, Malappuram", "Malappuram", "S", 3.4);
        addCollege("LMC", "Lourdes Matha College of Science and Tech, Thiruvananthapuram", "Thiruvananthapuram", "S", 3.4);
        addCollege("MBC", "Mar Baselios Christian College of Engg and Tech, Idukki", "Idukki", "S", 3.4);
        addCollege("MCC", "Musaliar College Of Engineering, Thiruvananthapuram", "Thiruvananthapuram", "S", 3.4);
        addCollege("MCK", "Musaliar College of Engineering & Tech, Pathanamthitta", "Pathanamthitta", "S", 3.4);
        addCollege("MCT", "Mohandas College of Engineering and Tech, Thiruvananthapuram", "Thiruvananthapuram", "S", 3.4);
        addCollege("MEC", "Malabar College of Engineering and Technology, Thrissur", "Thrissur", "S", 3.4);
        addCollege("MET", "Mets School of Engineering, Thrissur", "Thrissur", "S", 3.4);
        addCollege("MGC", "M G College of Engineering, Thiruvananthapuram", "Thiruvananthapuram", "S", 3.3);
        addCollege("MLT", "Malabar Institute of Technology, Kannur", "Kannur", "S", 3.3);
        addCollege("MUS", "Muslim Association College of Engg, Thiruvananthapuram", "Thiruvananthapuram", "S", 3.3);
        addCollege("MZC", "Mount Zion College of Engineering, Pathanamthitta", "Pathanamthitta", "S", 3.3);
        addCollege("NCE", "Nehru College of Engineering and Research Centre, Thrissur", "Thrissur", "S", 3.3);
        addCollege("NIE", "Nirmala College of Engineering Tech and Mgmt, Thrissur", "Thrissur", "S", 3.3);
        addCollege("PRC", "Providence College of Engineering, Chengannur", "Alappuzha", "S", 3.4);
        addCollege("RCE", "Royal College of Engineering and Technology, Thrissur", "Thrissur", "S", 3.3);
        addCollege("SPT", "Sreepathy Institute of Management and Tech, Palakkad", "Palakkad", "S", 3.3);
        addCollege("STC", "St Thomas College of Engineering and Tech, Alappuzha", "Alappuzha", "S", 3.3);
        addCollege("STI", "St. Thomas Institute For Science and Tech, Thiruvananthapuram", "Thiruvananthapuram", "S", 3.3);
        addCollege("STM", "St. Thomas College of Engineering and Tech, Kannur", "Kannur", "S", 3.3);
        addCollege("TCE", "TOMS College of Engineering, Kottayam", "Kottayam", "S", 3.2);
        addCollege("TCT", "Trinity College of Engineering, Thiruvananthapuram", "Thiruvananthapuram", "S", 3.2);
        addCollege("TEC", "Travancore Engineering College, Kollam", "Kollam", "S", 3.2);
        addCollege("TJE", "Thejus Engineering College, Thrissur", "Thrissur", "S", 3.3);
        addCollege("UNT", "Universal Engineering College, Thrissur", "Thrissur", "S", 3.2);
        addCollege("VIT", "VISAT Engineering College, Ernakulam", "Ernakulam", "S", 3.2);
        addCollege("VKE", "Valia Koonambaikulathamma College of Engg, Parippally", "Kollam", "S", 3.2);
        addCollege("VPE", "Mahaguru Institute of Technology, Mavelikkara", "Alappuzha", "S", 3.2);
        addCollege("VVT", "Veda Vyasa Institute of Technology, Malappuram", "Malappuram", "S", 3.1);
        addCollege("YCE", "Younus College of Engineering and Technology, Kollam", "Kollam", "S", 3.3);
        addCollege("AAE", "Al-Azhar College of Engineering and Technology, Idukki", "Idukki", "S", 3.2);
        addCollege("AAP", "Al-Ameen Engineering College, Palakkad", "Palakkad", "S", 3.2);
        addCollege("AME", "Rajadhani Institute of Science and Technology, Palakkad", "Palakkad", "S", 3.2);
        addCollege("CIM", "Mentor Academy for Design Innovation (MDiT), Ernakulam", "Ernakulam", "S", 3.2);
        addCollege("ECE", "ICCS College of Engineering and Management, Thrissur", "Thrissur", "S", 3.2);
        addCollege("GIK", "Gregorian Institute of Technology, Kottayam", "Kottayam", "S", 3.2);
        addCollege("HGW", "Holy Grace Academy of Engineering, Thrissur", "Thrissur", "S", 3.2);
        addCollege("IGW", "Indira Gandhi Institute of Engineering and Tech, Ernakulam", "Ernakulam", "S", 3.2);
        addCollege("JBT", "Jai Bharath College of Mgmt and Engg Tech, Ernakulam", "Ernakulam", "S", 3.2);
        addCollege("JIT", "John Cox Memorial C S I Institute of Tech, Thiruvananthapuram", "Thiruvananthapuram", "S", 3.3);
        addCollege("KIT", "Kottayam Institute of Technology and Science, Kottayam", "Kottayam", "S", 3.2);
        addCollege("KVE", "KVM College of Engineering & IT, Alappuzha", "Alappuzha", "S", 3.2);
        addCollege("MEE", "MES College of Engineering and Technology, Ernakulam", "Ernakulam", "S", 3.4);
        addCollege("MEK", "MES Institute of Technology and Management, Kollam", "Kollam", "S", 3.4);
        addCollege("MGE", "MGM College of Engineering & Technology, Ernakulam", "Ernakulam", "S", 3.2);
        addCollege("PAA", "P A Aziz College of Engineering & Tech, Thiruvananthapuram", "Thiruvananthapuram", "S", 3.2);
        addCollege("SIT", "Sarabhai Institute of Science and Tech, Thiruvananthapuram", "Thiruvananthapuram", "S", 3.2);
    }

    private static void initCourses() {
        addCourse("Computer Science and Engineering", "Computer Science & IT");
        addCourse("Computer Science and Engineering (Artificial Intelligence and Machine Learning)", "Computer Science & IT");
        addCourse("Artificial Intelligence and Data Science", "Computer Science & IT");
        addCourse("Computer Science and Engineering (Cyber Security)", "Computer Science & IT");
        addCourse("Computer Science and Engineering (Artificial Intelligence)", "Computer Science & IT");
        addCourse("Computer Science & Engineering (Data Science)", "Computer Science & IT");
        addCourse("Computer Science and Business Systems", "Computer Science & IT");
        addCourse("Computer Science & Design", "Computer Science & IT");
        addCourse("Information Technology", "Computer Science & IT");
        addCourse("Electronics and Communication Engineering", "Electronics & Electrical");
        addCourse("Electrical and Electronics Engineering", "Electronics & Electrical");
        addCourse("Electrical and Computer Engineering", "Electronics & Electrical");
        addCourse("Electronics & Biomedical Engineering", "Electronics & Electrical");
        addCourse("Electronics and Computer Science", "Electronics & Electrical");
        addCourse("Electronics Engineering (VLSI Design & Technology)", "Electronics & Electrical");
        addCourse("Applied Electronics & Instrumentation", "Electronics & Electrical");
        addCourse("Mechanical Engineering", "Mechanical & Allied");
        addCourse("Mechanical Engg. (Automobile)", "Mechanical & Allied");
        addCourse("Automobile Engineering", "Mechanical & Allied");
        addCourse("Mechatronics Engineering", "Mechanical & Allied");
        addCourse("Production Engineering", "Mechanical & Allied");
        addCourse("Civil Engineering", "Civil");
        addCourse("Chemical Engineering", "Specialized & Core");
        addCourse("Robotics and Artificial Intelligence", "Computer Science & IT");
        addCourse("Robotics & Automation", "Mechanical & Allied");
        addCourse("Food Technology", "Specialized & Core");
        addCourse("Bio Technology and Biochemical Engg.", "Specialized & Core");
        addCourse("Aeronautical Engineering", "Specialized & Core");
        addCourse("Naval Architecture & Ship Building Engineering", "Specialized & Core");
        addCourse("Safety & Fire Engineering", "Specialized & Core");
        addCourse("Dairy Technology.", "Specialized & Core");
    }

    private static void initCutoffs() {
        // Populating exact allotment cutoffs extracted directly from KEAM 2026 Phase 2 Allotment Last Rank Table PDF!
        AllotmentCutoff c;

        // ==========================================
        // 1. COMPUTER SCIENCE AND ENGINEERING (Pages 10-18)
        // ==========================================
        c = addCutoff("TVE", "Computer Science and Engineering", "G");
        c.addCutoff("SM", 584); c.addCutoff("EZ", 890); c.addCutoff("MU", 750); c.addCutoff("LA", 2086); c.addCutoff("DV", 1016);
        c.addCutoff("VK", 808); c.addCutoff("BH", 749); c.addCutoff("BX", 1495); c.addCutoff("KN", 4021); c.addCutoff("KU", 2593);
        c.addCutoff("SC", 7027); c.addCutoff("ST", 13156); c.addCutoff("EW", 1452); c.addOther("FW", 452); c.addOther("PD", 27362);

        c = addCutoff("TCR", "Computer Science and Engineering", "G");
        c.addCutoff("SM", 947); c.addCutoff("EZ", 1288); c.addCutoff("MU", 1051); c.addCutoff("LA", 4605); c.addCutoff("DV", 4586);
        c.addCutoff("VK", 1224); c.addCutoff("BH", 962); c.addCutoff("BX", 9522); c.addCutoff("KN", 4645); c.addCutoff("KU", 9593);
        c.addCutoff("SC", 8152); c.addCutoff("ST", 22632); c.addCutoff("EW", 2231); c.addOther("FW", 814); c.addOther("PD", 33336);

        c = addCutoff("MDL", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 1595); c.addCutoff("EZ", 2350); c.addCutoff("MU", 2038); c.addCutoff("LA", 4256); c.addCutoff("DV", 7062);
        c.addCutoff("VK", 2613); c.addCutoff("BH", 1964); c.addCutoff("BX", 6848); c.addCutoff("KN", 3789); c.addCutoff("KU", 16975);
        c.addCutoff("SC", 16285); c.addCutoff("ST", 36998); c.addCutoff("EW", 3178); c.addOther("FW", 1406); c.addOther("MG", 3493);

        c = addCutoff("TKM", "Computer Science and Engineering", "G");
        c.addCutoff("SM", 1596); c.addCutoff("EZ", 1818); c.addCutoff("MU", 1753); c.addCutoff("LA", 3568); c.addCutoff("DV", 8878);
        c.addCutoff("VK", 2079); c.addCutoff("BH", 2091); c.addCutoff("BX", 3102); c.addCutoff("KN", 9261); c.addCutoff("KU", 15551);
        c.addCutoff("SC", 13465); c.addCutoff("ST", 30898); c.addOther("FW", 1034); c.addOther("PD", 33740);

        c = addCutoff("MAC", "Computer Science and Engineering", "G");
        c.addCutoff("SM", 2286); c.addCutoff("EZ", 2463); c.addCutoff("MU", 2616); c.addCutoff("LA", 5533); c.addCutoff("DV", 8311);
        c.addCutoff("VK", 2953); c.addCutoff("BH", 2375); c.addCutoff("BX", 11928); c.addCutoff("KN", 14545);
        c.addCutoff("SC", 15637); c.addCutoff("ST", 33860); c.addOther("FW", 1881);

        c = addCutoff("KTE", "Computer Science and Engineering", "G");
        c.addCutoff("SM", 3149); c.addCutoff("EZ", 3764); c.addCutoff("MU", 3548); c.addCutoff("LA", 5328); c.addCutoff("DV", 11435);
        c.addCutoff("VK", 4028); c.addCutoff("BH", 3483); c.addCutoff("BX", 12678); c.addCutoff("KN", 15277); c.addCutoff("KU", 6912);
        c.addCutoff("SC", 19157); c.addCutoff("ST", 47357); c.addCutoff("EW", 4705); c.addOther("FW", 2152);

        c = addCutoff("PKD", "Computer Science and Engineering", "G");
        c.addCutoff("SM", 3552); c.addCutoff("EZ", 7202); c.addCutoff("MU", 4649); c.addCutoff("LA", 13042); c.addCutoff("DV", 12917);
        c.addCutoff("VK", 7172); c.addCutoff("BH", 6884); c.addCutoff("BX", 19786); c.addCutoff("KN", 6710); c.addCutoff("KU", 20495);
        c.addCutoff("SC", 24607); c.addCutoff("ST", 44341); c.addCutoff("EW", 8234); c.addOther("FW", 3405);

        c = addCutoff("KNR", "Computer Science and Engineering", "G");
        c.addCutoff("SM", 3561); c.addCutoff("EZ", 5585); c.addCutoff("MU", 4813); c.addCutoff("LA", 9348); c.addCutoff("DV", 12055);
        c.addCutoff("VK", 5208); c.addCutoff("BH", 5408); c.addCutoff("KU", 32405); c.addCutoff("SC", 21143); c.addCutoff("ST", 16602);
        c.addCutoff("EW", 7537); c.addOther("FW", 2936);

        c = addCutoff("NSS", "Computer Science and Engineering", "G");
        c.addCutoff("SM", 3594); c.addCutoff("EZ", 4923); c.addCutoff("MU", 4611); c.addCutoff("LA", 9276); c.addCutoff("DV", 12597);
        c.addCutoff("VK", 6794); c.addCutoff("BH", 4518); c.addCutoff("BX", 16707); c.addCutoff("KU", 15784); c.addCutoff("SC", 19921);
        c.addCutoff("ST", 52478); c.addCutoff("EW", 7010); c.addOther("FW", 2725);

        c = addCutoff("SCT", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 5711); c.addCutoff("EZ", 7131); c.addCutoff("MU", 6933); c.addCutoff("LA", 7650); c.addCutoff("DV", 10939);
        c.addCutoff("VK", 9991); c.addCutoff("BH", 6542); c.addCutoff("BX", 6129); c.addCutoff("KN", 19377); c.addCutoff("KU", 33053);
        c.addCutoff("SC", 23674); c.addCutoff("ST", 48666); c.addCutoff("EW", 11106); c.addOther("FW", 4784); c.addOther("MG", 9804);

        c = addCutoff("RET", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 5818); c.addCutoff("EZ", 7194); c.addCutoff("MU", 6845); c.addCutoff("LA", 11383); c.addCutoff("DV", 10860);
        c.addCutoff("VK", 11030); c.addCutoff("BH", 9769); c.addCutoff("BX", 27434); c.addCutoff("KN", 23661); c.addCutoff("KU", 24548);
        c.addCutoff("SC", 36364); c.addCutoff("ST", 56862); c.addOther("FW", 4820); c.addOther("YN", 10665);

        c = addCutoff("MUT", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 8224); c.addCutoff("EZ", 10468); c.addCutoff("MU", 9257); c.addCutoff("LA", 17919); c.addCutoff("DV", 23493);
        c.addCutoff("VK", 11624); c.addCutoff("BH", 11251); c.addCutoff("BX", 34208); c.addCutoff("KN", 52312); c.addCutoff("KU", 35951);
        c.addCutoff("SC", 41425); c.addCutoff("ST", 61410); c.addCutoff("EW", 17009); c.addOther("FW", 6917);

        c = addCutoff("CHN", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 9492); c.addCutoff("EZ", 12022); c.addCutoff("MU", 11170); c.addCutoff("LA", 18051); c.addCutoff("DV", 18130);
        c.addCutoff("VK", 12798); c.addCutoff("BH", 11818); c.addCutoff("BX", 19621); c.addCutoff("KN", 29908); c.addCutoff("KU", 24870);
        c.addCutoff("SC", 37496); c.addCutoff("EW", 15779); c.addOther("FW", 7727); c.addOther("MG", 20099);

        c = addCutoff("FIT", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 9697); c.addCutoff("EZ", 11379); c.addCutoff("MU", 10710); c.addCutoff("LA", 13463); c.addCutoff("DV", 14138);
        c.addCutoff("VK", 13091); c.addCutoff("BH", 11102); c.addCutoff("BX", 39752); c.addCutoff("KN", 30389); c.addCutoff("KU", 33044);
        c.addCutoff("SC", 36153); c.addCutoff("EW", 15579); c.addOther("FW", 8426);

        c = addCutoff("LBT", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 13233); c.addCutoff("EZ", 15842); c.addCutoff("MU", 16307); c.addCutoff("LA", 25191); c.addCutoff("DV", 31705);
        c.addCutoff("VK", 18864); c.addCutoff("BH", 15019); c.addCutoff("BX", 14829); c.addCutoff("KN", 34411); c.addCutoff("KU", 48484);
        c.addCutoff("SC", 48987); c.addCutoff("EW", 20608); c.addOther("FW", 9747); c.addOther("MG", 25904);

        c = addCutoff("MBT", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 13714); c.addCutoff("EZ", 16366); c.addCutoff("MU", 15472); c.addCutoff("LA", 22910); c.addCutoff("DV", 16438);
        c.addCutoff("VK", 15720); c.addCutoff("BH", 17034); c.addCutoff("BX", 16354); c.addCutoff("KU", 53358);
        c.addCutoff("SC", 47836); c.addCutoff("ST", 53228); c.addOther("FW", 13481);

        c = addCutoff("TLY", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 14209); c.addCutoff("EZ", 18936); c.addCutoff("MU", 15116); c.addCutoff("LA", 38788); c.addCutoff("DV", 37364);
        c.addCutoff("VK", 16304); c.addCutoff("BH", 15984); c.addCutoff("BX", 28154); c.addCutoff("KN", 32984);
        c.addCutoff("SC", 51348); c.addCutoff("EW", 26237); c.addOther("FW", 10354); c.addOther("MG", 26631);

        c = addCutoff("MGP", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 14441); c.addCutoff("EZ", 18475); c.addCutoff("MU", 15931); c.addCutoff("LA", 15857); c.addCutoff("DV", 28753);
        c.addCutoff("VK", 25961); c.addCutoff("BH", 15632); c.addCutoff("BX", 32846); c.addCutoff("KU", 31655);
        c.addCutoff("SC", 54372); c.addOther("FW", 12652);

        c = addCutoff("PRN", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 16395); c.addCutoff("EZ", 22083); c.addCutoff("MU", 18122); c.addCutoff("LA", 19976); c.addCutoff("DV", 23497);
        c.addCutoff("VK", 17585); c.addCutoff("BH", 20978); c.addCutoff("BX", 27675); c.addCutoff("SC", 50544); c.addCutoff("EW", 29046);
        c.addOther("FW", 10513); c.addOther("MG", 32306);

        c = addCutoff("VDA", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 17419); c.addCutoff("EZ", 22313); c.addCutoff("MU", 19195); c.addCutoff("LA", 51234); c.addCutoff("DV", 34928);
        c.addCutoff("VK", 22754); c.addCutoff("BH", 20077); c.addCutoff("SC", 56079); c.addCutoff("EW", 36807);
        c.addOther("FW", 10406); c.addOther("MG", 33743);

        c = addCutoff("AJC", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 17640); c.addCutoff("EZ", 22251); c.addCutoff("MU", 20916); c.addCutoff("LA", 30319); c.addCutoff("DV", 30954);
        c.addCutoff("VK", 23427); c.addCutoff("BH", 23011); c.addCutoff("BX", 32986); c.addCutoff("KU", 56363);
        c.addCutoff("SC", 60049); c.addOther("FW", 11721); c.addOther("YB", 30906);

        c = addCutoff("SCM", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 17825); c.addCutoff("EZ", 21702); c.addCutoff("MU", 20173); c.addCutoff("LA", 29841); c.addCutoff("DV", 31367);
        c.addCutoff("VK", 24475); c.addCutoff("BH", 21478); c.addCutoff("BX", 41044); c.addCutoff("KN", 60089); c.addCutoff("KU", 58921);
        c.addCutoff("SC", 59594); c.addCutoff("EW", 32960); c.addOther("FW", 13397);

        c = addCutoff("KSD", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 18154); c.addCutoff("EZ", 23219); c.addCutoff("MU", 20794); c.addCutoff("LA", 47508); c.addCutoff("DV", 56770);
        c.addCutoff("VK", 25454); c.addCutoff("BH", 23327); c.addCutoff("BX", 53579); c.addCutoff("KN", 46430);
        c.addCutoff("SC", 62557); c.addCutoff("ST", 22487); c.addCutoff("EW", 32850); c.addOther("FW", 11220); c.addOther("MG", 41204);

        c = addCutoff("CEC", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 18828); c.addCutoff("EZ", 22153); c.addCutoff("MU", 20387); c.addCutoff("LA", 27453); c.addCutoff("DV", 29211);
        c.addCutoff("VK", 23250); c.addCutoff("BH", 26619); c.addCutoff("KN", 31158); c.addCutoff("SC", 51907); c.addCutoff("EW", 32012);
        c.addOther("FW", 13558); c.addOther("MG", 48995);

        c = addCutoff("ADR", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 19471); c.addCutoff("EZ", 22509); c.addCutoff("MU", 22943); c.addCutoff("LA", 39348); c.addCutoff("DV", 40653);
        c.addCutoff("VK", 19622); c.addCutoff("BH", 24778); c.addCutoff("BX", 24725); c.addCutoff("KN", 58151);
        c.addCutoff("SC", 48838); c.addCutoff("EW", 28250); c.addOther("FW", 14605); c.addOther("MG", 41648);

        c = addCutoff("UCK", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 19858); c.addCutoff("EZ", 22671); c.addCutoff("MU", 25039); c.addCutoff("LA", 23700); c.addCutoff("DV", 42378);
        c.addCutoff("VK", 22832); c.addCutoff("BH", 25417); c.addCutoff("BX", 20470); c.addCutoff("KN", 30869); c.addCutoff("KU", 38592);
        c.addCutoff("SC", 52528); c.addCutoff("EW", 28171); c.addOther("FW", 13026); c.addOther("MG", 32517);

        c = addCutoff("CCE", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 20489); c.addCutoff("EZ", 25667); c.addCutoff("MU", 24926); c.addCutoff("LA", 39904); c.addCutoff("DV", 29348);
        c.addCutoff("VK", 31433); c.addCutoff("BH", 28992); c.addCutoff("BX", 44639); c.addCutoff("KN", 52289); c.addCutoff("KU", 33275);
        c.addCutoff("SC", 60719); c.addOther("FW", 14944);

        c = addCutoff("MES", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 21840); c.addCutoff("EZ", 38783); c.addCutoff("MU", 23111); c.addCutoff("DV", 40614); c.addCutoff("VK", 47882);
        c.addCutoff("BH", 29770); c.addCutoff("KN", 62232); c.addCutoff("SC", 64914); c.addOther("FW", 12894);

        c = addCutoff("ASI", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 22702); c.addCutoff("EZ", 27850); c.addCutoff("MU", 24866); c.addCutoff("LA", 34618); c.addCutoff("DV", 26915);
        c.addCutoff("VK", 27487); c.addCutoff("BH", 34330); c.addCutoff("BX", 45173); c.addCutoff("KN", 50738);
        c.addCutoff("SC", 65129); c.addCutoff("EW", 38961); c.addOther("FW", 17516);

        c = addCutoff("KGR", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 22846); c.addCutoff("EZ", 28053); c.addCutoff("MU", 26375); c.addCutoff("LA", 43267); c.addCutoff("DV", 55587);
        c.addCutoff("VK", 24264); c.addCutoff("BH", 37348); c.addCutoff("BX", 28364); c.addCutoff("KN", 44308);
        c.addCutoff("SC", 59314); c.addCutoff("EW", 53667); c.addOther("FW", 12109); c.addOther("MG", 49601);

        c = addCutoff("KNP", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 24532); c.addCutoff("EZ", 27371); c.addCutoff("MU", 26864); c.addCutoff("LA", 35722); c.addCutoff("DV", 38921);
        c.addCutoff("VK", 26250); c.addCutoff("BH", 28852); c.addCutoff("BX", 48836); c.addCutoff("KU", 53947);
        c.addCutoff("SC", 59073); c.addCutoff("EW", 43112); c.addOther("FW", 20653); c.addOther("MG", 55990);

        c = addCutoff("TKR", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 24868); c.addCutoff("EZ", 29169); c.addCutoff("MU", 30145); c.addCutoff("DV", 27525); c.addCutoff("VK", 25697);
        c.addCutoff("BH", 27272); c.addCutoff("SC", 60160); c.addCutoff("EW", 43183); c.addOther("FW", 16889); c.addOther("MG", 46366);

        c = addCutoff("JEC", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 25629); c.addCutoff("EZ", 34042); c.addCutoff("MU", 33022); c.addCutoff("LA", 63988); c.addCutoff("DV", 30372);
        c.addCutoff("VK", 37075); c.addCutoff("BH", 27006); c.addCutoff("KU", 54983); c.addCutoff("SC", 58337); c.addOther("FW", 16337);

        c = addCutoff("SJC", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 25818); c.addCutoff("EZ", 32773); c.addCutoff("MU", 32019); c.addCutoff("LA", 53381); c.addCutoff("DV", 42186);
        c.addCutoff("VK", 33910); c.addCutoff("BH", 30741); c.addCutoff("BX", 44044); c.addCutoff("SC", 65093); c.addOther("FW", 22005);

        c = addCutoff("UCC", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 26344); c.addCutoff("EZ", 32291); c.addCutoff("MU", 27117); c.addCutoff("LA", 38708); c.addCutoff("VK", 33349);
        c.addCutoff("BH", 36605); c.addCutoff("BX", 60253); c.addCutoff("SC", 64646); c.addCutoff("EW", 51455); c.addOther("FW", 13719);

        c = addCutoff("VJC", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 26645); c.addCutoff("EZ", 33706); c.addCutoff("MU", 29417); c.addCutoff("LA", 32685); c.addCutoff("DV", 46894);
        c.addCutoff("VK", 28384); c.addCutoff("BH", 42361); c.addCutoff("BX", 29435); c.addCutoff("KN", 57448);
        c.addCutoff("SC", 52957); c.addCutoff("ST", 64817); c.addOther("FW", 18756);

        c = addCutoff("CEA", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 29338); c.addCutoff("EZ", 31536); c.addCutoff("MU", 33588); c.addCutoff("LA", 52378); c.addCutoff("DV", 58904);
        c.addCutoff("VK", 36133); c.addCutoff("BH", 33882); c.addCutoff("BX", 47334); c.addCutoff("KN", 65265);
        c.addCutoff("SC", 61598); c.addCutoff("EW", 42157); c.addOther("FW", 18978); c.addOther("MG", 46961);

        c = addCutoff("PTA", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 30050); c.addCutoff("EZ", 34311); c.addCutoff("MU", 36067); c.addCutoff("DV", 41378); c.addCutoff("VK", 36311);
        c.addCutoff("BH", 34155); c.addCutoff("BX", 30518); c.addCutoff("SC", 57648); c.addCutoff("EW", 43422); c.addOther("FW", 23037);

        c = addCutoff("TOC", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 30252); c.addCutoff("EZ", 40284); c.addCutoff("MU", 33617); c.addCutoff("LA", 34316); c.addCutoff("DV", 39207);
        c.addCutoff("VK", 30946); c.addCutoff("BH", 39197); c.addCutoff("BX", 56559); c.addCutoff("KU", 52402);
        c.addCutoff("SC", 63664); c.addCutoff("EW", 61032); c.addOther("FW", 22134);

        c = addCutoff("KMC", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 31621); c.addCutoff("EZ", 38506); c.addCutoff("MU", 32688); c.addCutoff("VK", 36165); c.addCutoff("BH", 42655);
        c.addCutoff("SC", 52970); c.addOther("FW", 15084);

        c = addCutoff("MCE", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 34362); c.addCutoff("EZ", 45713); c.addCutoff("MU", 37255); c.addCutoff("LA", 35808); c.addCutoff("DV", 56255);
        c.addCutoff("VK", 44287); c.addCutoff("BH", 42624); c.addCutoff("BX", 36891); c.addCutoff("KN", 49352);
        c.addCutoff("SC", 58984); c.addOther("FW", 24514);

        c = addCutoff("TKI", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 34386); c.addCutoff("EZ", 40860); c.addCutoff("MU", 37817); c.addCutoff("LA", 44194); c.addCutoff("DV", 63757);
        c.addCutoff("VK", 54125); c.addCutoff("BH", 49391); c.addCutoff("BX", 55127); c.addCutoff("SC", 63707); c.addOther("FW", 22879);

        c = addCutoff("CEM", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 36435); c.addCutoff("EZ", 48299); c.addCutoff("MU", 43100); c.addCutoff("LA", 49702); c.addCutoff("DV", 56983);
        c.addCutoff("VK", 45074); c.addCutoff("BH", 38027); c.addCutoff("BX", 47488); c.addCutoff("KN", 62008);
        c.addCutoff("SC", 59474); c.addCutoff("ST", 65186); c.addCutoff("EW", 62182); c.addOther("FW", 17942);

        c = addCutoff("SHR", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 37078); c.addCutoff("EZ", 43624); c.addCutoff("MU", 43311); c.addCutoff("DV", 53417); c.addCutoff("BH", 41589);
        c.addCutoff("BX", 56568); c.addCutoff("SC", 62004); c.addOther("FW", 24079);

        c = addCutoff("JCE", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 37142); c.addCutoff("EZ", 43419); c.addCutoff("MU", 46348); c.addCutoff("VK", 60306); c.addCutoff("BH", 39255);
        c.addCutoff("SC", 63804); c.addCutoff("EW", 61086); c.addOther("FW", 23217);

        c = addCutoff("VML", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 38169); c.addCutoff("EZ", 47078); c.addCutoff("MU", 45826); c.addCutoff("DV", 49748); c.addCutoff("VK", 54032);
        c.addCutoff("BH", 42970); c.addCutoff("KN", 47664); c.addCutoff("SC", 57772); c.addCutoff("ST", 64668); c.addOther("FW", 19221);

        c = addCutoff("UCE", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 38451); c.addCutoff("EZ", 46660); c.addCutoff("MU", 43947); c.addCutoff("DV", 62469); c.addCutoff("VK", 46867);
        c.addCutoff("BH", 44225); c.addCutoff("SC", 62613); c.addCutoff("EW", 58176); c.addOther("FW", 21401);

        c = addCutoff("VAS", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 38902); c.addCutoff("EZ", 46186); c.addCutoff("MU", 47777); c.addCutoff("DV", 52364); c.addCutoff("VK", 52104);
        c.addCutoff("BH", 42819); c.addCutoff("KU", 43234); c.addCutoff("SC", 63637); c.addCutoff("EW", 64852); c.addOther("FW", 22195);

        c = addCutoff("PEC", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 39556); c.addCutoff("EZ", 49720); c.addCutoff("MU", 49935); c.addCutoff("LA", 59895); c.addCutoff("DV", 58097);
        c.addCutoff("VK", 39645); c.addCutoff("BH", 45056); c.addCutoff("SC", 62049); c.addCutoff("EW", 54306); c.addOther("FW", 19352);

        c = addCutoff("PRP", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 40124); c.addCutoff("EZ", 51689); c.addCutoff("MU", 47727); c.addCutoff("LA", 55334); c.addCutoff("DV", 48413);
        c.addCutoff("VK", 50491); c.addCutoff("BH", 54347); c.addCutoff("BX", 40389); c.addCutoff("SC", 64309); c.addCutoff("EW", 52387);
        c.addOther("FW", 21292);

        c = addCutoff("CEK", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 43332); c.addCutoff("EZ", 51469); c.addCutoff("MU", 49991); c.addCutoff("LA", 64843); c.addCutoff("DV", 54206);
        c.addCutoff("VK", 52103); c.addCutoff("BH", 55092); c.addCutoff("BX", 50490); c.addCutoff("SC", 62802); c.addCutoff("EW", 63045);

        c = addCutoff("PJR", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 43616); c.addCutoff("EZ", 49022); c.addCutoff("MU", 51878); c.addCutoff("LA", 56896); c.addCutoff("DV", 58045);
        c.addCutoff("VK", 51512); c.addCutoff("BH", 53572); c.addCutoff("BX", 44370); c.addCutoff("KU", 50347);
        c.addCutoff("SC", 63836); c.addCutoff("EW", 64501); c.addOther("FW", 28915);

        c = addCutoff("MHP", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 46232); c.addCutoff("EZ", 56396); c.addCutoff("MU", 49506); c.addCutoff("LA", 47296); c.addCutoff("BH", 46570);
        c.addCutoff("SC", 58851); c.addOther("FW", 34393);

        c = addCutoff("RIE", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 46457); c.addCutoff("EZ", 57656); c.addCutoff("MU", 53750); c.addCutoff("LA", 59180); c.addCutoff("VK", 65037);
        c.addCutoff("BH", 60728); c.addCutoff("KN", 51770); c.addCutoff("SC", 64828); c.addCutoff("EW", 63330); c.addOther("FW", 27989);

        c = addCutoff("KME", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 47326); c.addCutoff("EZ", 57480); c.addCutoff("MU", 51268); c.addCutoff("LA", 64200); c.addCutoff("DV", 50875);
        c.addCutoff("VK", 60903); c.addCutoff("BH", 52348); c.addCutoff("KU", 55539); c.addCutoff("SC", 62155); c.addOther("FW", 27307);

        c = addCutoff("NCE", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 48283); c.addCutoff("EZ", 53666); c.addCutoff("MU", 54413); c.addCutoff("LA", 58647); c.addCutoff("DV", 49187);
        c.addCutoff("VK", 49227); c.addCutoff("BH", 56230); c.addCutoff("SC", 50925); c.addOther("FW", 26710);

        c = addCutoff("AEC", "Computer Science and Engineering", "N");
        c.addCutoff("SM", 48509); c.addCutoff("EZ", 54662); c.addCutoff("MU", 57618); c.addCutoff("DV", 63059); c.addCutoff("VK", 50512);
        c.addCutoff("BH", 55793); c.addCutoff("SC", 61995); c.addCutoff("EW", 57974); c.addOther("FW", 25270);

        c = addCutoff("SNG", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 52190); c.addCutoff("EZ", 65279); c.addCutoff("MU", 64325); c.addCutoff("LA", 64342); c.addCutoff("DV", 56047);
        c.addCutoff("VK", 59815); c.addCutoff("BH", 62506); c.addCutoff("SC", 61708); c.addOther("FW", 31824);

        c = addCutoff("LMC", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 52386); c.addCutoff("EZ", 64520); c.addCutoff("MU", 52895); c.addCutoff("DV", 56803); c.addCutoff("VK", 56351);
        c.addCutoff("BH", 53878); c.addCutoff("BX", 53230); c.addCutoff("SC", 63815); c.addOther("FW", 33277);

        c = addCutoff("SBC", "Computer Science and Engineering", "S");
        c.addCutoff("SM", 52998); c.addCutoff("EZ", 61674); c.addCutoff("MU", 64942); c.addCutoff("LA", 64970); c.addCutoff("DV", 57795);
        c.addCutoff("VK", 62223); c.addCutoff("BH", 64417); c.addCutoff("EW", 64113); c.addOther("FW", 37555);

        // ==========================================
        // 2. ELECTRONICS AND COMMUNICATION (Pages 19-26)
        // ==========================================
        c = addCutoff("TVE", "Electronics and Communication Engineering", "G");
        c.addCutoff("SM", 601); c.addCutoff("EZ", 1079); c.addCutoff("MU", 795); c.addCutoff("LA", 2187); c.addCutoff("DV", 1641);
        c.addCutoff("VK", 1024); c.addCutoff("BH", 925); c.addCutoff("BX", 1385); c.addCutoff("KN", 2035); c.addCutoff("KU", 3384);
        c.addCutoff("SC", 7147); c.addCutoff("ST", 25313); c.addCutoff("EW", 1759); c.addOther("FW", 286);

        c = addCutoff("TCR", "Electronics and Communication Engineering", "G");
        c.addCutoff("SM", 1331); c.addCutoff("EZ", 1700); c.addCutoff("MU", 1502); c.addCutoff("LA", 5903); c.addCutoff("DV", 4118);
        c.addCutoff("VK", 1662); c.addCutoff("BH", 1896); c.addCutoff("BX", 5619); c.addCutoff("KN", 10649); c.addCutoff("KU", 11873);
        c.addCutoff("SC", 13393); c.addCutoff("ST", 47633); c.addCutoff("EW", 3436); c.addOther("FW", 1071);

        c = addCutoff("TKM", "Electronics and Communication Engineering", "G");
        c.addCutoff("SM", 1878); c.addCutoff("EZ", 2366); c.addCutoff("MU", 1962); c.addCutoff("LA", 5037); c.addCutoff("DV", 2672);
        c.addCutoff("VK", 2884); c.addCutoff("BH", 2201); c.addCutoff("BX", 5375); c.addCutoff("KN", 14447); c.addCutoff("KU", 8299);
        c.addCutoff("SC", 19705); c.addCutoff("ST", 39309); c.addOther("FW", 1341);

        c = addCutoff("MDL", "Electronics and Communication Engineering", "N");
        c.addCutoff("SM", 2064); c.addCutoff("EZ", 2665); c.addCutoff("MU", 2442); c.addCutoff("LA", 3266); c.addCutoff("DV", 5798);
        c.addCutoff("VK", 3272); c.addCutoff("BH", 2428); c.addCutoff("BX", 3809); c.addCutoff("KN", 13647); c.addCutoff("KU", 12394);
        c.addCutoff("SC", 18488); c.addCutoff("ST", 53664); c.addCutoff("EW", 6459); c.addOther("FW", 2046); c.addOther("MG", 4202);

        c = addCutoff("MAC", "Electronics and Communication Engineering", "G");
        c.addCutoff("SM", 3083); c.addCutoff("EZ", 3665); c.addCutoff("MU", 3592); c.addCutoff("LA", 8072); c.addCutoff("DV", 11354);
        c.addCutoff("VK", 3895); c.addCutoff("BH", 4075); c.addCutoff("BX", 12918); c.addCutoff("KN", 19303); c.addCutoff("KU", 21754);
        c.addCutoff("SC", 26798); c.addOther("FW", 2343);

        c = addCutoff("TRV", "Electronics and Communication Engineering", "G");
        c.addCutoff("SM", 3694); c.addCutoff("EZ", 4793); c.addCutoff("MU", 4274); c.addCutoff("LA", 6440); c.addCutoff("DV", 14126);
        c.addCutoff("VK", 4562); c.addCutoff("BH", 4414); c.addCutoff("BX", 7449); c.addCutoff("KN", 17553); c.addCutoff("KU", 22192);
        c.addCutoff("SC", 22863); c.addCutoff("EW", 6769); c.addOther("FW", 2822);

        c = addCutoff("KTE", "Electronics and Communication Engineering", "G");
        c.addCutoff("SM", 3781); c.addCutoff("EZ", 4070); c.addCutoff("MU", 4045); c.addCutoff("LA", 8585); c.addCutoff("DV", 5920);
        c.addCutoff("VK", 4063); c.addCutoff("BH", 4472); c.addCutoff("KU", 19929); c.addCutoff("SC", 20780); c.addCutoff("ST", 9826);
        c.addCutoff("EW", 5429); c.addOther("FW", 2635);

        c = addCutoff("KKE", "Electronics and Communication Engineering", "G");
        c.addCutoff("SM", 4538); c.addCutoff("EZ", 9064); c.addCutoff("MU", 6922); c.addCutoff("LA", 13685); c.addCutoff("DV", 21174);
        c.addCutoff("VK", 8780); c.addCutoff("BH", 8456); c.addCutoff("BX", 22546); c.addCutoff("KN", 16117);
        c.addCutoff("SC", 30879); c.addCutoff("ST", 64727); c.addCutoff("EW", 11821); c.addOther("FW", 4491);

        c = addCutoff("PKD", "Electronics and Communication Engineering", "G");
        c.addCutoff("SM", 4707); c.addCutoff("EZ", 7796); c.addCutoff("MU", 6977); c.addCutoff("LA", 12111); c.addCutoff("DV", 26607);
        c.addCutoff("VK", 8526); c.addCutoff("BH", 7736); c.addCutoff("BX", 44354); c.addCutoff("KN", 22123); c.addCutoff("KU", 38085);
        c.addCutoff("SC", 30268); c.addCutoff("ST", 54871); c.addCutoff("EW", 10723); c.addOther("FW", 5165);

        c = addCutoff("KNR", "Electronics and Communication Engineering", "G");
        c.addCutoff("SM", 4744); c.addCutoff("EZ", 7014); c.addCutoff("MU", 7022); c.addCutoff("LA", 13494); c.addCutoff("DV", 19503);
        c.addCutoff("VK", 6648); c.addCutoff("BH", 6456); c.addCutoff("BX", 39978); c.addCutoff("KN", 26857); c.addCutoff("KU", 35302);
        c.addCutoff("SC", 28685); c.addCutoff("ST", 61816); c.addCutoff("EW", 11164); c.addOther("FW", 3485);

        c = addCutoff("NSS", "Electronics and Communication Engineering", "G");
        c.addCutoff("SM", 4763); c.addCutoff("EZ", 5575); c.addCutoff("MU", 5205); c.addCutoff("LA", 10580); c.addCutoff("DV", 13079);
        c.addCutoff("VK", 5850); c.addCutoff("BH", 5199); c.addCutoff("BX", 22988); c.addCutoff("KN", 15827); c.addCutoff("KU", 19653);
        c.addCutoff("SC", 29214); c.addCutoff("ST", 50605); c.addCutoff("EW", 8119); c.addOther("FW", 3195);

        c = addCutoff("RET", "Electronics and Communication Engineering", "S");
        c.addCutoff("SM", 7411); c.addCutoff("EZ", 9564); c.addCutoff("MU", 8136); c.addCutoff("LA", 13774); c.addCutoff("DV", 15740);
        c.addCutoff("VK", 18513); c.addCutoff("BH", 8847); c.addCutoff("BX", 15686); c.addCutoff("KN", 16599); c.addCutoff("KU", 25655);
        c.addCutoff("SC", 45442); c.addOther("FW", 6904);

        c = addCutoff("SCT", "Electronics and Communication Engineering", "N");
        c.addCutoff("SM", 7717); c.addCutoff("EZ", 8815); c.addCutoff("MU", 8402); c.addCutoff("LA", 12714); c.addCutoff("DV", 33475);
        c.addCutoff("VK", 7864); c.addCutoff("BH", 9399); c.addCutoff("BX", 10327); c.addCutoff("KU", 49087);
        c.addCutoff("SC", 38615); c.addCutoff("EW", 13892); c.addOther("FW", 7829); c.addOther("MG", 11551);

        c = addCutoff("MUT", "Electronics and Communication Engineering", "S");
        c.addCutoff("SM", 9055); c.addCutoff("EZ", 10203); c.addCutoff("MU", 9623); c.addCutoff("LA", 9196); c.addCutoff("DV", 11737);
        c.addCutoff("VK", 17082); c.addCutoff("BH", 9542); c.addCutoff("SC", 53998); c.addCutoff("EW", 16314); c.addOther("FW", 8981);

        c = addCutoff("FIT", "Electronics and Communication Engineering", "S");
        c.addCutoff("SM", 10515); c.addCutoff("EZ", 13395); c.addCutoff("MU", 11630); c.addCutoff("LA", 20549); c.addCutoff("DV", 20590);
        c.addCutoff("VK", 12718); c.addCutoff("BH", 11794); c.addCutoff("BX", 35388); c.addCutoff("KN", 42531); c.addCutoff("KU", 22190);
        c.addCutoff("SC", 51675); c.addCutoff("EW", 18778); c.addOther("FW", 8369);

        c = addCutoff("CHN", "Electronics and Communication Engineering", "N");
        c.addCutoff("SM", 10767); c.addCutoff("EZ", 11388); c.addCutoff("MU", 12250); c.addCutoff("LA", 18716); c.addCutoff("DV", 28659);
        c.addCutoff("VK", 12009); c.addCutoff("BH", 12567); c.addCutoff("BX", 51425); c.addCutoff("KN", 23388);
        c.addCutoff("SC", 48513); c.addCutoff("EW", 16826); c.addOther("FW", 10218); c.addOther("MG", 20732);

        // ==========================================
        // 3. ARTIFICIAL INTELLIGENCE & DATA SCIENCE / AIML (Pages 1-2, 40-44)
        // ==========================================
        c = addCutoff("MAC", "Artificial Intelligence and Machine Learning", "G");
        c.addCutoff("SM", 5743); c.addCutoff("EZ", 6709); c.addCutoff("MU", 6009); c.addCutoff("LA", 12375); c.addCutoff("DV", 21628);
        c.addCutoff("VK", 8262); c.addCutoff("BH", 7373); c.addCutoff("KU", 29083); c.addCutoff("SC", 29207); c.addCutoff("ST", 27546);
        c.addOther("FW", 4442);

        c = addCutoff("TKM", "Computer Science and Engineering (Artificial Intelligence)", "G");
        c.addCutoff("SM", 2218); c.addCutoff("EZ", 2740); c.addCutoff("MU", 2561); c.addCutoff("LA", 6363); c.addCutoff("DV", 4616);
        c.addCutoff("VK", 3707); c.addCutoff("BH", 2488); c.addCutoff("SC", 13969); c.addCutoff("ST", 59175); c.addOther("FW", 1770);

        c = addCutoff("SCT", "Computer Science and Engineering (Artificial Intelligence and Machine Learning)", "N");
        c.addCutoff("SM", 7154); c.addCutoff("EZ", 8836); c.addCutoff("MU", 7601); c.addCutoff("LA", 9499); c.addCutoff("DV", 13769);
        c.addCutoff("BH", 8465); c.addCutoff("KN", 26457); c.addCutoff("SC", 23649); c.addCutoff("ST", 48828); c.addCutoff("EW", 12389);
        c.addOther("FW", 6378); c.addOther("MG", 11495);

        c = addCutoff("RET", "Artificial Intelligence and Data Science", "S");
        c.addCutoff("SM", 8990); c.addCutoff("EZ", 10219); c.addCutoff("MU", 9634); c.addCutoff("LA", 15070); c.addCutoff("DV", 21899);
        c.addCutoff("SC", 52893); c.addOther("FW", 8227); c.addOther("YN", 15520);

        c = addCutoff("CHN", "Computer Science and Engineering (Artificial Intelligence and Machine Learning)", "N");
        c.addCutoff("SM", 11327); c.addCutoff("EZ", 11790); c.addCutoff("MU", 12150); c.addCutoff("LA", 21823); c.addCutoff("VK", 13926);
        c.addCutoff("BH", 11646); c.addCutoff("KU", 53860); c.addCutoff("SC", 41752); c.addCutoff("EW", 18109);
        c.addOther("FW", 9169); c.addOther("MG", 22622);

        c = addCutoff("MUT", "Artificial Intelligence and Data Science", "S");
        c.addCutoff("SM", 14525); c.addCutoff("EZ", 17127); c.addCutoff("MU", 15519); c.addCutoff("LA", 19881); c.addCutoff("DV", 28895);
        c.addCutoff("VK", 14526); c.addCutoff("BH", 16749); c.addCutoff("SC", 57551); c.addCutoff("EW", 29394); c.addOther("FW", 12930);

        c = addCutoff("MBT", "Computer Science and Engineering (Artificial Intelligence)", "S");
        c.addCutoff("SM", 16855); c.addCutoff("EZ", 19795); c.addCutoff("MU", 21621); c.addCutoff("LA", 19060); c.addCutoff("DV", 27954);
        c.addCutoff("VK", 29953); c.addCutoff("BH", 17818); c.addCutoff("SC", 46926); c.addOther("FW", 12982);

        c = addCutoff("LBT", "Artificial Intelligence and Data Science", "N");
        c.addCutoff("SM", 17097); c.addCutoff("EZ", 22751); c.addCutoff("MU", 19058); c.addCutoff("LA", 33403); c.addCutoff("VK", 19197);
        c.addCutoff("BH", 19635); c.addCutoff("SC", 54905); c.addCutoff("EW", 36324); c.addOther("MG", 36059);

        c = addCutoff("SCM", "Computer Science and Engineering (Artificial Intelligence)", "S");
        c.addCutoff("SM", 19683); c.addCutoff("EZ", 22786); c.addCutoff("MU", 20918); c.addCutoff("LA", 23174); c.addCutoff("DV", 21019);
        c.addCutoff("VK", 20770); c.addCutoff("BH", 23554); c.addCutoff("SC", 51839); c.addCutoff("EW", 31391); c.addOther("FW", 15816);

        c = addCutoff("KSD", "Computer Science and Engineering (Artificial Intelligence and Data Science)", "N");
        c.addCutoff("SM", 21014); c.addCutoff("EZ", 28034); c.addCutoff("MU", 21501); c.addCutoff("BH", 24402); c.addCutoff("SC", 63185);
        c.addCutoff("EW", 34539); c.addOther("FW", 10254); c.addOther("MG", 39838);

        c = addCutoff("AJC", "Artificial Intelligence and Data Science", "S");
        c.addCutoff("SM", 25066); c.addCutoff("EZ", 28859); c.addCutoff("MU", 30317); c.addCutoff("LA", 46291); c.addCutoff("VK", 32414);
        c.addCutoff("BH", 27022); c.addCutoff("SC", 58854); c.addOther("FW", 19966);

        c = addCutoff("CEC", "Artificial Intelligence and Data Science", "N");
        c.addCutoff("SM", 25934); c.addCutoff("EZ", 31926); c.addCutoff("MU", 28327); c.addCutoff("LA", 26967); c.addCutoff("DV", 43882);
        c.addCutoff("BH", 29641); c.addCutoff("BX", 53944); c.addCutoff("SC", 44431); c.addCutoff("EW", 56101); c.addOther("FW", 17674);

        // ==========================================
        // 4. ELECTRICAL AND ELECTRONICS (Pages 26-31)
        // ==========================================
        c = addCutoff("TVE", "Electrical and Electronics Engineering", "G");
        c.addCutoff("SM", 1307); c.addCutoff("EZ", 1933); c.addCutoff("MU", 1547); c.addCutoff("LA", 3740); c.addCutoff("DV", 2468);
        c.addCutoff("VK", 2382); c.addCutoff("BH", 1692); c.addCutoff("BX", 1959); c.addCutoff("KN", 8670); c.addCutoff("KU", 7124);
        c.addCutoff("SC", 13444); c.addCutoff("ST", 32316); c.addCutoff("EW", 3842); c.addOther("FW", 973);

        c = addCutoff("TCR", "Electrical and Electronics Engineering", "G");
        c.addCutoff("SM", 2601); c.addCutoff("EZ", 3093); c.addCutoff("MU", 3318); c.addCutoff("LA", 8239); c.addCutoff("DV", 10431);
        c.addCutoff("VK", 4440); c.addCutoff("BH", 3425); c.addCutoff("BX", 7538); c.addCutoff("KN", 7300); c.addCutoff("KU", 17016);
        c.addCutoff("SC", 16932); c.addCutoff("ST", 48326); c.addCutoff("EW", 6244); c.addOther("FW", 2083);

        c = addCutoff("TKM", "Electrical and Electronics Engineering", "G");
        c.addCutoff("SM", 3349); c.addCutoff("EZ", 3803); c.addCutoff("MU", 3484); c.addCutoff("LA", 7221); c.addCutoff("DV", 8153);
        c.addCutoff("VK", 5314); c.addCutoff("BH", 4476); c.addCutoff("BX", 9444); c.addCutoff("KN", 15623); c.addCutoff("KU", 15224);
        c.addCutoff("SC", 27545); c.addCutoff("ST", 40153); c.addOther("FW", 2406);

        c = addCutoff("MDL", "Electrical and Electronics Engineering", "N");
        c.addCutoff("SM", 4282); c.addCutoff("EZ", 5537); c.addCutoff("MU", 4801); c.addCutoff("LA", 8631); c.addCutoff("BH", 6263);
        c.addCutoff("KU", 29143); c.addCutoff("SC", 32601); c.addCutoff("EW", 9486); c.addOther("FW", 3474); c.addOther("MG", 7780);

        c = addCutoff("MAC", "Electrical and Electronics Engineering", "G");
        c.addCutoff("SM", 5730); c.addCutoff("EZ", 7615); c.addCutoff("MU", 6165); c.addCutoff("LA", 12331); c.addCutoff("DV", 19064);
        c.addCutoff("VK", 9198); c.addCutoff("BH", 7611); c.addCutoff("BX", 12625); c.addCutoff("KN", 21411); c.addCutoff("KU", 32481);
        c.addCutoff("SC", 37905); c.addCutoff("ST", 46309); c.addOther("FW", 4039);

        c = addCutoff("TRV", "Electrical and Electronics Engineering", "G");
        c.addCutoff("SM", 5941); c.addCutoff("EZ", 7849); c.addCutoff("MU", 7064); c.addCutoff("LA", 11088); c.addCutoff("DV", 17233);
        c.addCutoff("VK", 10604); c.addCutoff("BH", 7624); c.addCutoff("KN", 30652); c.addCutoff("KU", 33009);
        c.addCutoff("SC", 29043); c.addCutoff("ST", 62826); c.addCutoff("EW", 9275); c.addOther("FW", 4144);

        c = addCutoff("KNR", "Electrical and Electronics Engineering", "G");
        c.addCutoff("SM", 6176); c.addCutoff("EZ", 9458); c.addCutoff("MU", 8766); c.addCutoff("LA", 15495); c.addCutoff("DV", 35014);
        c.addCutoff("VK", 9722); c.addCutoff("BH", 9166); c.addCutoff("BX", 9211); c.addCutoff("KN", 27323); c.addCutoff("KU", 45990);
        c.addCutoff("SC", 46293); c.addCutoff("ST", 47699); c.addCutoff("EW", 17125); c.addOther("FW", 7127);

        c = addCutoff("KTE", "Electrical and Electronics Engineering", "G");
        c.addCutoff("SM", 6230); c.addCutoff("EZ", 8051); c.addCutoff("MU", 6682); c.addCutoff("LA", 8931); c.addCutoff("DV", 14737);
        c.addCutoff("VK", 7059); c.addCutoff("BH", 7101); c.addCutoff("BX", 11024); c.addCutoff("KN", 31714);
        c.addCutoff("SC", 31068); c.addCutoff("EW", 9083); c.addOther("FW", 4128);

        c = addCutoff("PKD", "Electrical and Electronics Engineering", "G");
        c.addCutoff("SM", 6749); c.addCutoff("EZ", 11571); c.addCutoff("MU", 8508); c.addCutoff("LA", 18996); c.addCutoff("DV", 34635);
        c.addCutoff("VK", 11864); c.addCutoff("BH", 11933); c.addCutoff("BX", 19095); c.addCutoff("SC", 45133); c.addCutoff("ST", 49955);
        c.addCutoff("EW", 13913); c.addOther("FW", 7332);

        c = addCutoff("NSS", "Electrical and Electronics Engineering", "G");
        c.addCutoff("SM", 6834); c.addCutoff("EZ", 8380); c.addCutoff("MU", 8180); c.addCutoff("LA", 17646); c.addCutoff("DV", 27996);
        c.addCutoff("VK", 9349); c.addCutoff("BH", 8774); c.addCutoff("BX", 24705); c.addCutoff("KN", 17086); c.addCutoff("KU", 22454);
        c.addCutoff("SC", 42985); c.addCutoff("ST", 64295); c.addCutoff("EW", 12163); c.addOther("FW", 6330);

        c = addCutoff("RET", "Electrical and Electronics Engineering", "S");
        c.addCutoff("SM", 10460); c.addCutoff("EZ", 12193); c.addCutoff("MU", 11466); c.addCutoff("LA", 18990); c.addCutoff("VK", 10843);
        c.addCutoff("BH", 12738); c.addCutoff("BX", 39777); c.addCutoff("SC", 39456); c.addOther("FW", 10363);

        c = addCutoff("MUT", "Electrical and Electronics Engineering", "S");
        c.addCutoff("SM", 13153); c.addCutoff("EZ", 20516); c.addCutoff("MU", 13824); c.addCutoff("LA", 15561); c.addCutoff("DV", 30527);
        c.addCutoff("VK", 17108); c.addCutoff("BH", 16614); c.addCutoff("SC", 47775); c.addCutoff("EW", 34431); c.addOther("FW", 10766);

        c = addCutoff("FIT", "Electrical and Electronics Engineering", "S");
        c.addCutoff("SM", 14202); c.addCutoff("EZ", 16029); c.addCutoff("MU", 15137); c.addCutoff("LA", 25789); c.addCutoff("DV", 20984);
        c.addCutoff("VK", 16772); c.addCutoff("BH", 14974); c.addCutoff("SC", 65370); c.addCutoff("EW", 31124); c.addOther("FW", 13934);

        c = addCutoff("CHN", "Electrical and Electronics Engineering", "N");
        c.addCutoff("SM", 14854); c.addCutoff("EZ", 15364); c.addCutoff("MU", 16082); c.addCutoff("LA", 27324); c.addCutoff("DV", 57685);
        c.addCutoff("BH", 15029); c.addCutoff("SC", 48038); c.addCutoff("EW", 18310); c.addOther("FW", 11709);

        // ==========================================
        // 5. MECHANICAL ENGINEERING (Pages 34-40)
        // ==========================================
        c = addCutoff("TVE", "Mechanical Engineering", "G");
        c.addCutoff("SM", 2323); c.addCutoff("EZ", 3159); c.addCutoff("MU", 2867); c.addCutoff("LA", 6897); c.addCutoff("DV", 13135);
        c.addCutoff("VK", 3779); c.addCutoff("BH", 3163); c.addCutoff("BX", 5956); c.addCutoff("KN", 5449); c.addCutoff("KU", 27021);
        c.addCutoff("SC", 17858); c.addCutoff("ST", 56795); c.addCutoff("EW", 6201); c.addOther("FW", 1681);

        c = addCutoff("TCR", "Mechanical Engineering", "G");
        c.addCutoff("SM", 4267); c.addCutoff("EZ", 5089); c.addCutoff("MU", 4873); c.addCutoff("LA", 12519); c.addCutoff("DV", 16668);
        c.addCutoff("VK", 7513); c.addCutoff("BH", 4480); c.addCutoff("BX", 6863); c.addCutoff("KN", 14882); c.addCutoff("KU", 14009);
        c.addCutoff("SC", 25950); c.addCutoff("ST", 60366); c.addCutoff("EW", 10638); c.addOther("FW", 2872);

        c = addCutoff("TKM", "Mechanical Engineering", "G");
        c.addCutoff("SM", 6120); c.addCutoff("EZ", 7414); c.addCutoff("MU", 6798); c.addCutoff("LA", 11182); c.addCutoff("DV", 22748);
        c.addCutoff("VK", 6880); c.addCutoff("BH", 7111); c.addCutoff("BX", 10429); c.addCutoff("KN", 18817); c.addCutoff("KU", 29290);
        c.addCutoff("SC", 35002); c.addCutoff("ST", 63491); c.addOther("FW", 4775);

        c = addCutoff("MDL", "Mechanical Engineering", "N");
        c.addCutoff("SM", 9182); c.addCutoff("EZ", 11391); c.addCutoff("MU", 9943); c.addCutoff("LA", 12914); c.addCutoff("DV", 24363);
        c.addCutoff("VK", 12425); c.addCutoff("BH", 11961); c.addCutoff("SC", 28112); c.addCutoff("ST", 64797); c.addCutoff("EW", 11590);
        c.addOther("FW", 6229); c.addOther("MG", 13029);

        c = addCutoff("MAC", "Mechanical Engineering", "G");
        c.addCutoff("SM", 9159); c.addCutoff("EZ", 10686); c.addCutoff("MU", 9416); c.addCutoff("LA", 15286); c.addCutoff("DV", 29241);
        c.addCutoff("VK", 10645); c.addCutoff("BH", 11414); c.addCutoff("BX", 20961); c.addCutoff("KN", 31783); c.addCutoff("KU", 33291);
        c.addCutoff("SC", 42034); c.addOther("FW", 7465);

        c = addCutoff("KTE", "Mechanical Engineering", "G");
        c.addCutoff("SM", 9494); c.addCutoff("EZ", 11058); c.addCutoff("MU", 9835); c.addCutoff("LA", 14398); c.addCutoff("DV", 25224);
        c.addCutoff("VK", 9811); c.addCutoff("BH", 10916); c.addCutoff("BX", 30405); c.addCutoff("KN", 22909); c.addCutoff("KU", 46236);
        c.addCutoff("SC", 41701); c.addCutoff("EW", 13118); c.addOther("FW", 7024);

        c = addCutoff("TRV", "Mechanical Engineering", "G");
        c.addCutoff("SM", 9620); c.addCutoff("EZ", 11257); c.addCutoff("MU", 10695); c.addCutoff("LA", 16037); c.addCutoff("DV", 24921);
        c.addCutoff("VK", 12340); c.addCutoff("BH", 13180); c.addCutoff("KN", 34662); c.addCutoff("KU", 58396);
        c.addCutoff("SC", 41428); c.addCutoff("EW", 15882); c.addOther("FW", 7594);

        c = addCutoff("KKE", "Mechanical Engineering", "G");
        c.addCutoff("SM", 10199); c.addCutoff("EZ", 14783); c.addCutoff("MU", 12501); c.addCutoff("LA", 20310); c.addCutoff("DV", 15154);
        c.addCutoff("VK", 14465); c.addCutoff("BH", 14491); c.addCutoff("KN", 37711); c.addCutoff("KU", 46376);
        c.addCutoff("SC", 46065); c.addCutoff("EW", 23724); c.addOther("FW", 9700);

        c = addCutoff("KNR", "Mechanical Engineering", "G");
        c.addCutoff("SM", 10208); c.addCutoff("EZ", 13731); c.addCutoff("MU", 12995); c.addCutoff("LA", 22276); c.addCutoff("DV", 39953);
        c.addCutoff("VK", 16145); c.addCutoff("BH", 14248); c.addCutoff("SC", 46311); c.addCutoff("EW", 21938); c.addOther("FW", 9539);

        c = addCutoff("NSS", "Mechanical Engineering", "G");
        c.addCutoff("SM", 10236); c.addCutoff("EZ", 12091); c.addCutoff("MU", 11966); c.addCutoff("LA", 18431); c.addCutoff("DV", 31620);
        c.addCutoff("VK", 13164); c.addCutoff("BH", 11715); c.addCutoff("BX", 30196); c.addCutoff("KN", 26817); c.addCutoff("KU", 45152);
        c.addCutoff("SC", 45083); c.addCutoff("ST", 49157); c.addCutoff("EW", 17539); c.addOther("FW", 9067);

        c = addCutoff("PKD", "Mechanical Engineering", "G");
        c.addCutoff("SM", 10244); c.addCutoff("EZ", 15770); c.addCutoff("MU", 12889); c.addCutoff("LA", 26395); c.addCutoff("DV", 33693);
        c.addCutoff("VK", 14238); c.addCutoff("BH", 14895); c.addCutoff("SC", 49464); c.addCutoff("EW", 19841); c.addOther("FW", 9899);

        c = addCutoff("SCT", "Mechanical Engineering", "N");
        c.addCutoff("SM", 13186); c.addCutoff("EZ", 14925); c.addCutoff("MU", 14998); c.addCutoff("LA", 20899); c.addCutoff("DV", 35062);
        c.addCutoff("VK", 14304); c.addCutoff("BH", 13595); c.addCutoff("BX", 16289); c.addCutoff("KN", 39054); c.addCutoff("KU", 44414);
        c.addCutoff("SC", 50838); c.addCutoff("EW", 23313); c.addOther("FW", 11886);

        c = addCutoff("RET", "Mechanical Engineering", "S");
        c.addCutoff("SM", 16392); c.addCutoff("EZ", 19438); c.addCutoff("MU", 17790); c.addCutoff("LA", 23931); c.addCutoff("DV", 25361);
        c.addCutoff("VK", 18997); c.addCutoff("BH", 17144); c.addCutoff("BX", 40468); c.addCutoff("KN", 62207);
        c.addCutoff("SC", 62870); c.addCutoff("ST", 65345); c.addOther("FW", 13553);

        c = addCutoff("MUT", "Mechanical Engineering", "S");
        c.addCutoff("SM", 18573); c.addCutoff("EZ", 20061); c.addCutoff("MU", 24131); c.addCutoff("LA", 42613); c.addCutoff("DV", 39118);
        c.addCutoff("VK", 19022); c.addCutoff("BH", 20384); c.addCutoff("SC", 60761); c.addCutoff("EW", 51145); c.addOther("FW", 14711);

        c = addCutoff("FIT", "Mechanical Engineering", "S");
        c.addCutoff("SM", 20440); c.addCutoff("EZ", 24461); c.addCutoff("MU", 21958); c.addCutoff("LA", 41124); c.addCutoff("DV", 38482);
        c.addCutoff("VK", 24164); c.addCutoff("BH", 23873); c.addCutoff("BX", 48769); c.addCutoff("KN", 45641); c.addCutoff("KU", 54086);
        c.addCutoff("SC", 64671); c.addCutoff("EW", 36130); c.addOther("FW", 17774);

        // ==========================================
        // 6. CIVIL ENGINEERING (Pages 3-8)
        // ==========================================
        c = addCutoff("TVE", "Civil Engineering", "G");
        c.addCutoff("SM", 4984); c.addCutoff("EZ", 6132); c.addCutoff("MU", 6143); c.addCutoff("LA", 8676); c.addCutoff("DV", 20382);
        c.addCutoff("VK", 7239); c.addCutoff("BH", 5772); c.addCutoff("BX", 5392); c.addCutoff("KN", 13625); c.addCutoff("KU", 20392);
        c.addCutoff("SC", 15550); c.addCutoff("ST", 39690); c.addCutoff("EW", 11758); c.addOther("FW", 3282);

        c = addCutoff("TCR", "Civil Engineering", "G");
        c.addCutoff("SM", 8100); c.addCutoff("EZ", 10188); c.addCutoff("MU", 8560); c.addCutoff("LA", 20914); c.addCutoff("DV", 16721);
        c.addCutoff("VK", 9642); c.addCutoff("BH", 11798); c.addCutoff("BX", 15651); c.addCutoff("KN", 17603); c.addCutoff("KU", 26008);
        c.addCutoff("SC", 28032); c.addCutoff("ST", 50023); c.addCutoff("EW", 16894); c.addOther("FW", 5031);

        c = addCutoff("TKM", "Civil Engineering", "G");
        c.addCutoff("SM", 8758); c.addCutoff("EZ", 11041); c.addCutoff("MU", 8970); c.addCutoff("LA", 10752); c.addCutoff("DV", 21004);
        c.addCutoff("VK", 10871); c.addCutoff("BH", 14020); c.addCutoff("BX", 14893); c.addCutoff("KN", 30218); c.addCutoff("KU", 31593);
        c.addCutoff("SC", 28649); c.addCutoff("ST", 58930); c.addOther("FW", 6324);

        c = addCutoff("TRV", "Civil Engineering", "G");
        c.addCutoff("SM", 10673); c.addCutoff("EZ", 13127); c.addCutoff("MU", 12577); c.addCutoff("LA", 15344); c.addCutoff("DV", 31141);
        c.addCutoff("VK", 12143); c.addCutoff("BH", 15053); c.addCutoff("BX", 11727); c.addCutoff("SC", 33388); c.addCutoff("ST", 49511);
        c.addCutoff("EW", 18593); c.addOther("FW", 8577);

        c = addCutoff("KNR", "Civil Engineering", "G");
        c.addCutoff("SM", 11535); c.addCutoff("EZ", 16794); c.addCutoff("MU", 14483); c.addCutoff("LA", 43926); c.addCutoff("DV", 33746);
        c.addCutoff("VK", 13355); c.addCutoff("BH", 16252); c.addCutoff("BX", 38268); c.addCutoff("KN", 48559);
        c.addCutoff("SC", 44476); c.addCutoff("ST", 38362); c.addCutoff("EW", 23998); c.addOther("FW", 7337);

        c = addCutoff("PKD", "Civil Engineering", "G");
        c.addCutoff("SM", 11765); c.addCutoff("EZ", 17421); c.addCutoff("MU", 14755); c.addCutoff("LA", 44883); c.addCutoff("DV", 39048);
        c.addCutoff("VK", 19796); c.addCutoff("BH", 16939); c.addCutoff("BX", 37077); c.addCutoff("KN", 45506); c.addCutoff("KU", 47412);
        c.addCutoff("SC", 48450); c.addCutoff("ST", 51922); c.addCutoff("EW", 36511); c.addOther("FW", 12063);

        c = addCutoff("KKE", "Civil Engineering", "G");
        c.addCutoff("SM", 11788); c.addCutoff("EZ", 16043); c.addCutoff("MU", 14255); c.addCutoff("LA", 30329); c.addCutoff("DV", 27835);
        c.addCutoff("VK", 12615); c.addCutoff("BH", 13185); c.addCutoff("BX", 16652); c.addCutoff("KN", 53942); c.addCutoff("KU", 47343);
        c.addCutoff("SC", 38875); c.addCutoff("ST", 48077); c.addCutoff("EW", 25559); c.addOther("FW", 7134);

        c = addCutoff("MAC", "Civil Engineering", "G");
        c.addCutoff("SM", 11829); c.addCutoff("EZ", 13833); c.addCutoff("MU", 12895); c.addCutoff("LA", 24651); c.addCutoff("DV", 32024);
        c.addCutoff("VK", 13977); c.addCutoff("BH", 15233); c.addCutoff("BX", 26154); c.addCutoff("KN", 33182); c.addCutoff("KU", 29451);
        c.addCutoff("SC", 43253); c.addCutoff("ST", 61172); c.addOther("FW", 9528);

        c = addCutoff("KTE", "Civil Engineering", "G");
        c.addCutoff("SM", 11841); c.addCutoff("EZ", 13573); c.addCutoff("MU", 13035); c.addCutoff("LA", 20636); c.addCutoff("DV", 25658);
        c.addCutoff("VK", 13936); c.addCutoff("BH", 15180); c.addCutoff("BX", 15511); c.addCutoff("KN", 32727); c.addCutoff("KU", 38237);
        c.addCutoff("SC", 35079); c.addCutoff("ST", 60958); c.addCutoff("EW", 17938); c.addOther("FW", 9029);

        c = addCutoff("NSS", "Civil Engineering", "G");
        c.addCutoff("SM", 11743); c.addCutoff("EZ", 17756); c.addCutoff("MU", 14634); c.addCutoff("LA", 26176); c.addCutoff("DV", 36912);
        c.addCutoff("VK", 19046); c.addCutoff("BH", 17885); c.addCutoff("BX", 34578); c.addCutoff("KN", 29081); c.addCutoff("KU", 43287);
        c.addCutoff("SC", 48293); c.addCutoff("ST", 53225); c.addCutoff("EW", 27379); c.addOther("FW", 11122);

        c = addCutoff("RET", "Civil Engineering", "S");
        c.addCutoff("SM", 16367); c.addCutoff("EZ", 22931); c.addCutoff("MU", 19067); c.addCutoff("LA", 21544); c.addCutoff("BH", 18299);
        c.addCutoff("SC", 56997); c.addOther("FW", 15469);

        c = addCutoff("MUT", "Civil Engineering", "S");
        c.addCutoff("SM", 25033); c.addCutoff("EZ", 35844); c.addCutoff("MU", 26966); c.addCutoff("LA", 25185); c.addCutoff("VK", 29782);
        c.addCutoff("BH", 31347); c.addCutoff("BX", 47370); c.addCutoff("SC", 58513); c.addCutoff("EW", 49347); c.addOther("FW", 20123);

        c = addCutoff("FIT", "Civil Engineering", "S");
        c.addCutoff("SM", 28849); c.addCutoff("EZ", 35012); c.addCutoff("MU", 36734); c.addCutoff("LA", 38094); c.addCutoff("DV", 48786);
        c.addCutoff("VK", 51331); c.addCutoff("BH", 32157); c.addCutoff("BX", 46873); c.addCutoff("KU", 47459);
        c.addCutoff("SC", 63956); c.addCutoff("ST", 62325); c.addOther("FW", 22324);

        // ==========================================
        // 7. CHEMICAL & SPECIALIZED BRANCHES (Pages 9, 33, 40, 48, 52)
        // ==========================================
        c = addCutoff("TCR", "Chemical Engineering", "G");
        c.addCutoff("SM", 6213); c.addCutoff("EZ", 8287); c.addCutoff("MU", 7206); c.addCutoff("LA", 12673); c.addCutoff("DV", 15535);
        c.addCutoff("VK", 9013); c.addCutoff("BH", 7974); c.addCutoff("BX", 12283); c.addCutoff("KN", 21799); c.addCutoff("KU", 48843);
        c.addCutoff("SC", 27537); c.addCutoff("ST", 50506); c.addCutoff("EW", 11393); c.addOther("FW", 3540);

        c = addCutoff("TKM", "Chemical Engineering", "G");
        c.addCutoff("SM", 6708); c.addCutoff("EZ", 8406); c.addCutoff("MU", 7439); c.addCutoff("LA", 12253); c.addCutoff("DV", 18691);
        c.addCutoff("VK", 9603); c.addCutoff("BH", 8141); c.addCutoff("BX", 11203); c.addCutoff("KU", 32443);
        c.addCutoff("SC", 30078); c.addOther("FW", 4597);

        c = addCutoff("KKE", "Chemical Engineering", "G");
        c.addCutoff("SM", 7318); c.addCutoff("EZ", 10834); c.addCutoff("MU", 9959); c.addCutoff("LA", 15145); c.addCutoff("DV", 24341);
        c.addCutoff("VK", 10256); c.addCutoff("BH", 10323); c.addCutoff("BX", 13995); c.addCutoff("KN", 38024); c.addCutoff("KU", 49693);
        c.addCutoff("SC", 36683); c.addCutoff("ST", 65352); c.addCutoff("EW", 15507); c.addOther("FW", 6873);

        c = addCutoff("TRV", "Information Technology", "G");
        c.addCutoff("SM", 7023); c.addCutoff("EZ", 8664); c.addCutoff("MU", 7264); c.addCutoff("LA", 17227); c.addCutoff("DV", 24811);
        c.addCutoff("VK", 7483); c.addCutoff("BH", 7322); c.addCutoff("SC", 38624); c.addCutoff("EW", 14517); c.addOther("FW", 5510);

        c = addCutoff("PKD", "Information Technology", "G");
        c.addCutoff("SM", 9645); c.addCutoff("EZ", 11094); c.addCutoff("MU", 10329); c.addCutoff("LA", 27604); c.addCutoff("DV", 41097);
        c.addCutoff("VK", 10282); c.addCutoff("BH", 10552); c.addCutoff("BX", 30914); c.addCutoff("KN", 35473);
        c.addCutoff("SC", 41107); c.addCutoff("EW", 15634); c.addOther("FW", 7015);

        c = addCutoff("IDK", "Information Technology", "G");
        c.addCutoff("SM", 10384); c.addCutoff("EZ", 17159); c.addCutoff("MU", 14024); c.addCutoff("LA", 42176); c.addCutoff("DV", 45487);
        c.addCutoff("VK", 24942); c.addCutoff("BH", 29972); c.addCutoff("BX", 54643); c.addCutoff("KN", 53647);
        c.addCutoff("SC", 54611); c.addCutoff("EW", 33960); c.addOther("FW", 11270);

        c = addCutoff("KTE", "Robotics and Artificial Intelligence", "G");
        c.addCutoff("SM", 6507); c.addCutoff("EZ", 7709); c.addCutoff("MU", 6899); c.addCutoff("LA", 7454); c.addCutoff("DV", 9144);
        c.addCutoff("VK", 7199); c.addCutoff("BH", 6552); c.addCutoff("SC", 26184); c.addCutoff("ST", 48199); c.addCutoff("EW", 9745);
        c.addOther("FW", 4434);

        c = addCutoff("IDK", "Robotics and Artificial Intelligence", "G");
        c.addCutoff("SM", 9017); c.addCutoff("EZ", 14290); c.addCutoff("MU", 12929); c.addCutoff("LA", 32948); c.addCutoff("DV", 45427);
        c.addCutoff("VK", 23146); c.addCutoff("BH", 17835); c.addCutoff("SC", 43132); c.addCutoff("EW", 30862); c.addOther("FW", 8610);

        c = addCutoff("MDL", "Electronics Engineering (VLSI Design & Technology)", "N");
        c.addCutoff("SM", 4438); c.addCutoff("EZ", 4776); c.addCutoff("MU", 6246); c.addCutoff("LA", 11983); c.addCutoff("DV", 21722);
        c.addCutoff("BH", 5222); c.addCutoff("SC", 30004); c.addCutoff("EW", 10330); c.addOther("FW", 3549);

        c = addCutoff("TCR", "Production Engineering", "G");
        c.addCutoff("SM", 11741); c.addCutoff("EZ", 14781); c.addCutoff("MU", 12621); c.addCutoff("LA", 15149); c.addCutoff("DV", 24238);
        c.addCutoff("VK", 14096); c.addCutoff("BH", 14688); c.addCutoff("BX", 45715); c.addCutoff("KN", 40103); c.addCutoff("KU", 57074);
        c.addCutoff("SC", 40574); c.addCutoff("EW", 18895); c.addOther("FW", 9274);
    }
}
