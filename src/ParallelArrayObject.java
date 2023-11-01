public class ParallelArrayObject {

    private long id;
    private double total;
    private int grupo;

    public ParallelArrayObject(long _id, double _total, int _grupo) {
        id = _id;
        total = _total;
        grupo = _grupo;
    }

    public long getId() {
        return id;
    }

    public void setId(long _id) {
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
