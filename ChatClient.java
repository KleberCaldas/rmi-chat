import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.util.*;

public class ChatClient{
	
	public static void main(String args[]){
		
		ChatClientInterf h = null;
		
		try{
			h = new ChatClientImpl();
		}catch(RemoteException e1){
			e1.printStackTrace();
		}
		
		//chamando servidor
	
		try{
			Registry reg = LocateRegistry.getRegistry("localhost", 9921);
			ChatServerInterf stub = (ChatServerInterf) reg.lookup("ServerChatRMI");
			
			System.out.println("Digite um nickname para entratar no chat: ");
			
			Scanner entrada = new Scanner(System.in);
			String mensagem = entrada.nextLine();
			h.setNickName(mensagem);
			
			//entrando no chat
			stub.login(h);
			
			System.out.println("-------------------------------------------------------------");
			System.out.println("----------- BEM VINDO AO SISTEMA DE CHAT          -----------");
			System.out.println("-------------------------------------------------------------");
			System.out.println("\n");
			System.out.println("----------- DIGITE SUA MENSAGEM E TECLE [ENTER]   -----------");
			System.out.println("----------- DIGITE [SAIR] PARA SAIR DO CHAT       -----------");
			System.out.println("\n");
			
			do{
				mensagem = entrada.nextLine();
				if(!mensagem.equalsIgnoreCase("[SAIR]")){
					stub.enviarMSG(h,mensagem);
				}
			}while(!mensagem.equalsIgnoreCase("[SAIR]"));
		
			//Saindo do chat
			stub.logoff(h);
			h = null;
			stub = null;
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}