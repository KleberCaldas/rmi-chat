import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.AlreadyBoundException;
import java.util.*;
import java.text.*;

public class ChatServer implements ChatServerInterf{
	
	
	private List<ChatClientInterf> clientes;
	private SimpleDateFormat dtf;
	
	public static void main(String args[]){
		
		ChatServer servidor = new ChatServer();
		
		try{
			servidor.inicializar();
		}catch(RemoteException | AlreadyBoundException e){
			e.printStackTrace();
		}
	}
	
	//Sobe o servidor
	public void inicializar() throws RemoteException, AlreadyBoundException{
		//exportar objeto para servidor
		
		clientes = new ArrayList<ChatClientInterf>();
		dtf = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
		
		ChatServerInterf stub = (ChatServerInterf)UnicastRemoteObject.exportObject(this,0);
	
		//registrar a porta de escuta
		Registry reg = LocateRegistry.createRegistry(9921);
	
		//ouvir a porta de escuta
		reg.bind("ServerChatRMI", stub);
	
	}
	
	//Metodos
	
	
	@Override
	public void enviarMSG(ChatClientInterf idCliente, String mensagem) throws RemoteException{
		
		String nick = idCliente.getNickName();
		
		if(mensagem.equalsIgnoreCase("[LISTAR]")){
			String nicks = "";
			for (ChatClientInterf Cliente : clientes){
				if(!nicks.equalsIgnoreCase("")){
					nicks = nicks + ", " + Cliente.getNickName();
				}
				else{
				nicks = Cliente.getNickName();
				}
			}
			idCliente.echoMensagem("Estao na sala os usuarios: " + nicks);
			System.out.println(dtf.format(new Date()) + " - comando enviado pelo nick " + nick + ": " + mensagem);
		}
		else{
			for(ChatClientInterf Cliente : clientes){
				if(!Cliente.equals(idCliente)){
					Cliente.echoMensagem(dtf.format(new Date()) + " - " + nick + " disse: " + mensagem);
				}
			}
			System.out.println(dtf.format(new Date()) + " - mensagem enviada por " + nick + ": " + mensagem);
		}
		
	}
	
	//Login
	@Override
	public synchronized void login(ChatClientInterf obj) throws RemoteException{
		
		if(!clientes.contains(obj)){
			clientes.add(obj);
			System.out.println(dtf.format(new Date()) + " - " + obj.getNickName() + " entrou no chat!");
		}
		
		for(ChatClientInterf Cliente : clientes){
			Cliente.echoMensagem(dtf.format(new Date()) + " - " + obj.getNickName() + " entrou no chat! ");
		}
	}
	
	@Override
	public synchronized void logoff(ChatClientInterf obj) throws RemoteException{
		
		if(!clientes.contains(obj)){
			clientes.remove(obj);
			System.out.println(dtf.format(new Date()) + " - " + obj.getNickName() + " saiu do chat!");
		}
		
		for(ChatClientInterf Cliente : clientes){
			Cliente.echoMensagem(dtf.format(new Date()) + " - " + obj.getNickName() + " saiu do chat! ");
		}
	}
	

}