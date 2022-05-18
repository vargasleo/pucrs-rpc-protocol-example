import java.io.Serializable;
import java.math.BigDecimal;

public class Conta implements Serializable {
    private String id;
    private String senha;
    private BigDecimal saldo;

    public Conta(String id, String senha, BigDecimal saldo) {
        this.id = id;
        this.senha = senha;
        this.saldo = saldo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id='" + id + '\'' +
                ", senha='" + senha + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
