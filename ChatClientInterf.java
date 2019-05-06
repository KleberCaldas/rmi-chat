import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientInterf extends Remote {
	
	//echoMensagem o servidor manda mensagem
	public void echoMensagem(String mensagem) throws RemoteException;
	public void setNickName(String nickname) throws RemoteException;
	public String getNickName() throws RemoteException;

}