
public abstract class Conta implements IConta {

	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;
	protected Credito credito;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
		saldo -= valor;
	}

	@Override
	public void depositar(double valor) {
		saldo += valor;
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		this.sacar(valor);
		contaDestino.depositar(valor);
	}

	public void solicitarCredito(double limite) {
		if (limite <= this.numero * 1000) {
			this.credito = new Credito(limite);
		} else {
			this.credito = new Credito(this.numero * 1000);
		}
	}

	public void pagarCredito(double valor) {
		if (this.credito != null) {
			if (this.credito.getLimite() - this.credito.getValor() - valor >= 0) {
				this.credito.setValor(this.credito.getValor() + valor);
			} else {
				System.out.println("Valor excede o limite do crédito");
			}
		} else {
			System.out.println("Conta não possui crédito");
		}
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
		System.out.println(String.format("Fatura: %.2f", this.credito.getValor()));
		System.out
				.println(String.format("Limite disponivel: %.2f", this.credito.getLimite() - this.credito.getValor()));
	}
}
