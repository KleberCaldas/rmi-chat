import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClientInterf{

	private static final long serialVersionUID = 1L;
	
	private String nickName;
	
	protected ChatClientImpl() throws RemoteException{
		super();
	}
	
	@Override
	public void echoMensagem(String mensagem) throws RemoteException{
		System.out.println(mensagem);
	}
	
	public String getNickName(){
		return nickName;
	}
	
	public void setNickName(String nickName){
		this.nickName = nickName;
	}
}