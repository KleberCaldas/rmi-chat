import java.io.Serializable;

public class ObjetoMensagem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String mensagem;
	
	public ObjetoMensagem(){
		
	}
	
	public String getMensagem(){
		return mensagem;
	}
	
	public void setMensagem(String mensagem){
		this.mensagem = mensagem;
	}

}