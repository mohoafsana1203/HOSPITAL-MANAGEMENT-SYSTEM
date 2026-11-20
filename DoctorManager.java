// DoctorManager.java
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorManager implements EntityManager<Doctor> {
    private List<Doctor> doctors = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void add(Doctor d) {
        d.setId(nextId++);
        doctors.add(d);
    }

    @Override
    public void update(int id, Doctor updated) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId() == id) {
                updated.setId(id);
                doctors.set(i, updated);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        doctors.removeIf(d -> d.getId() == id);
    }

    @Override
    public Doctor findById(int id) {
        return doctors.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Doctor> getAll() {
        return new ArrayList<>(doctors);
    }

    @Override
    public List<Doctor> search(String keyword) {
        return doctors.stream()
                .filter(d -> d.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                            d.getSpecialization().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
