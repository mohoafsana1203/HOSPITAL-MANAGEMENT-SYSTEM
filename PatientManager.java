// PatientManager.java
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatientManager implements EntityManager<Patient> {
    private List<Patient> patients = new ArrayList<>();
    private int nextId = 1;

    @Override
    public void add(Patient p) {
        p.setId(nextId++);
        patients.add(p);
    }

    @Override
    public void update(int id, Patient updated) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId() == id) {
                updated.setId(id);
                patients.set(i, updated);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        patients.removeIf(p -> p.getId() == id);
    }

    @Override
    public Patient findById(int id) {
        return patients.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Patient> getAll() {
        return new ArrayList<>(patients);
    }

    @Override
    public List<Patient> search(String keyword) {
        return patients.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                            p.getDisease().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
}
