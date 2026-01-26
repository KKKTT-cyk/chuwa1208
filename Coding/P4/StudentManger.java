package P4;

import java.util.*;

/*
Create StudentManager class with:
    private List<Student> students - use ArrayList
    private Map<String, Student> studentMap - use HashMap
    public void addStudent(Student student) - add to both list and map
    public List<Student> getTopStudents(int n) - return top n students by score (use sorting)
    public Set<String> getUniqueNames() - return unique names using Set
    public Map<Integer, List<Student>> groupByScore() - group students by score ranges (0-59, 60-79, 80-100)
    public void removeStudentById(String id) - remove from both collections
 */
public class StudentManger {
    private List<Student> students = new ArrayList<>();
    private Map<String, Student> studentMap = new HashMap<>();
    public void addStudent(Student student){
        String id = student.getId();

        if (studentMap.containsKey(id)){
            Student old = studentMap.get(id);
            studentMap.remove(old);
        }
        students.add(student);
        studentMap.put(id, student);
    }

    public List<Student> getTopStudents(int n) {
        List<Student> copy = new ArrayList<>(students);
        Collections.sort(copy, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.compare(o2.getScore(), o1.getScore());
            }
        });
        if (n < 0) n =0;
        if (n > copy.size()) n = copy.size();

        return  copy.subList(0, n);
    }
    // get Unique name
    public Set<String> getUniqueNames(){
        Set<String> names = new HashSet<>();
        for (Student s : students){
            names.add(s.getName());
        }
        return names;
    }

    //group by 0-59, 60-79, 80-100
    public Map<Integer, List<Student>> groupByScore(){
        Map<Integer, List<Student>> groups = new HashMap<>();
        groups.put(0, new ArrayList<Student>());
        groups.put(60, new ArrayList<Student>());
        groups.put(80, new ArrayList<Student>());

        for (Student s : students){
            int score = s.getScore();
            int bucket = getBucket(score);
            groups.get(bucket).add(s);
        }
        return groups;
    }
    public int getBucket(int score){
        if (score < 60) return 0;
        if (score < 80) return 60;
        return 80;
    }
    // remove removeStudentById
    public void removeStudentById(String id){
        Student removed = studentMap.remove(id);
        if (removed != null){
            students.remove(removed);
        }
    }

    public Student getStudentById(String id){
        return studentMap.get(id);
    }
    public List<Student> getAllStudents(){
        return new ArrayList<>(students);
    }
}
