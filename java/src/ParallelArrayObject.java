public class ParallelArrayObject {

    private int id;
    private float total;
    private byte grupo;

    public ParallelArrayObject(int _id, float _total, byte _grupo) {
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float _total) {
        total = _total;
    }

    public byte getGrupo() {
        return grupo;
    }

    public void setGrupo(byte _grupo) {
        grupo = _grupo;
    }
}
