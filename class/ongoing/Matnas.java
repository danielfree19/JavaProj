package ongoing;
public class Matnas extends Region{
    private final int id;
    public Matnas(int id,Employee Diractor,Employee Vice,Employee Secretery){
        super(Diractor,Vice,Secretery);
        this.id = id;
    }
    public int getID(){
        return id;
    }
}