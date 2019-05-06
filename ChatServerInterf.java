import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerInterf extends Remote{
	
	public void enviarMSG(ChatClientInterf obj,String mensagem) throws RemoteException;
	
	//Registra callback
	public void login(ChatClientInterf obj) throws RemoteException;
	public void logoff(ChatClientInterf obj) throws RemoteException;
}