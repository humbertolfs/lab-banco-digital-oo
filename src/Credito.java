public class Credito {
    private double valor;
    private double limite;

    public Credito(double limite) {
        this.limite = limite;
    }

    public double getValor() {
        return valor;
    }

    public double getLimite() {
        return limite;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }
}
