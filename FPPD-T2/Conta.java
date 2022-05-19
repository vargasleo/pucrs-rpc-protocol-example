import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Conta implements Serializable {
    private String id;
    private String senha;
    private String usuario;
    private BigDecimal saldo;

    private Map<String, Integer> tokens = new HashMap<>();

    public Conta(String usuario, String senha, BigDecimal saldo) {
        this.usuario = usuario;
        this.senha = senha;
        this.saldo = saldo;
    }

    public Map<String, Integer> getTokens() {
        return tokens;
    }

    public void addToken(String token) {
        this.tokens.put(token, 0);
    }
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
                ", usuario='" + usuario + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
