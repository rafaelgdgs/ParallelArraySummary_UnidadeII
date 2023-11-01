public class ParallelArrayObject {

    private int id;
    private double total;
    private int grupo;

    public ParallelArrayObject(int _id, double _total, int _grupo) {
        id = _id;
        total = _total;
        grupo = _grupo;
    }

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        id = _id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double _total) {
        total = _total;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int _grupo) {
        grupo = _grupo;
    }
}
