public class Armas
{
    String nombre;
    int danoExtra;

    public Armas(String nombre, int danoExtra) {
        this.nombre = nombre;
        this.danoExtra = danoExtra;
    }

  public int getDaño() {

    return this.danoExtra;
  }
}

