// Patient.java
public class Patient extends Person {
    private String disease;
    private String bloodGroup;
    private int age;

    public Patient() {}

    public Patient(int id, String name, String email, String phone, String address, 
                   String disease, String bloodGroup, int age) {
        super(id, name, email, phone, address);
        this.disease = disease;
        this.bloodGroup = bloodGroup;
        this.age = age;
    }

    public String getDisease() { return disease; }
    public void setDisease(String disease) { this.disease = disease; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String getRole() {
        return "Patient";
    }
}
