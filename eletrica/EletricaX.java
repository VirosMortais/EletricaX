import java.util.Scanner;

public class EletricaX {

/*Este programa es una aplicación de facturación de electricidad en Java. Utiliza la clase Scanner para recibir input del usuario.
*El programa pide al usuario que ingrese el número de contrato y la potencia contratada de un cliente, luego calcula el precio de la potencia contratada, pide la cantidad de kW consumidos, 
*calcula el precio por kW consumido, calcula el importe total, calcula el incremento en el importe y muestra el informe del cliente con todos estos datos. 
*El programa pregunta al usuario si desea ver otro cliente y si la respuesta es sí, repite el proceso, si la respuesta es no, termina el programa.*/

	public static void main(String[] args) {
			Scanner in = new Scanner(System.in);
			
			float kwTotal, precio, kWContratado, precioKw, importe, importefinal;
			
			
			//Variables para guardar el número de contrato y la potencia contratada
			String numContratos, potenciaContratada;
			
			//Inicializamos el total de kW a 0
			kwTotal = 0;
			
			boolean seguir = true, validar = false;
			
			//Este do-while se ejecutará mientras seguir sea verdadero
			do {
				//Pedimos el número de contrato
				numContratos = pedirNumContrato(in);
				
				 //Pedimos la potencia contratada
				potenciaContratada = pedirPotencias(in);			
				
				//Calculamos el precio de la potencia contratada				
				precio = potenciaPrecio(potenciaContratada);
				
				//Pedimos la cantidad de kW consumidos
				kWContratado= pedirKw(in);			
				
				//Calculamos el precio por kW consumido
				precioKw = calcPrecioKw(kWContratado);			
				
				//Calculamos el importe total
				importe = calcImporte(precio, precioKw);			
				
				//Calculamos el incremento en el importe
				importefinal = calcIncremento(kWContratado, importe);
				
				//Aumentamos el total de kW consumidos
				kwTotal += kWContratado;
				
				//Llamamos a la función informe para mostrar los datos del cliente
				informe( in,  numContratos,  kwTotal,  potenciaContratada,  importefinal, importe,  kWContratado);
				
				//descarta la primera línea vacía para evitar problemas con el método nextLine()
				in.nextLine();
				
				do {
					//Preguntamos si quiere ver otro cliente
					System.out.print("¿Quieres ver otro cliente? (s/n) ");
					
					
					
					String respuesta = in.nextLine();
				
					//verifica si la respuesta es "s" o "n" y establece la variable "seguir" en consecuencia
					if(respuesta.equalsIgnoreCase("s")) {
						seguir = true;
						validar = false;
					} else if(respuesta.equalsIgnoreCase("n")) {
						seguir = false;
						validar = false;
					} else {
						//si la respuesta no es "s" ni "n", se informa al usuario de ingresar una respuesta valida
					    System.err.println("Ingresa una respuesta valida (s/n)");
					    validar = true;
					}
				}while(validar);
			}while(seguir);
			
			System.out.println("-------------------------------------------------------------");
			fin();
				
			
	}
	
	/*
	 *  Este método se encarga de pedir al usuario que ingrese un número de contrato y verifica que el formato sea correcto. 
	 * Si el formato no es correcto, se muestra un mensaje de error y se vuelve a pedir el número de contrato hasta que se ingrese uno válido. 
	 * Finalmente, se devuelve el número de contrato válido ingresado por el usuario.
	 * */
	public static String pedirNumContrato(Scanner in) {
		//Declaracion de la variable contrato
		String contrato;
		
		//Se imprime en pantalla un mensaje para pedir el numero de contrato
		System.out.print("Escribir el numero de contrato: ");
		
		//Asignacion de la entrada del usuario a la variable contrato
		contrato = in.nextLine();
		
		//Declaracion de la variable sw con valor true
		boolean sw = true;
		
		//Mientras sw sea true se ejecutara el codigo dentro del while
		while(sw) {

			//Verifica si el número de contrato tiene el formato correcto. (ddd-dddd)
			if((contrato.matches("\\d{3}-\\d{4}"))) 
				// Si cumple con el formato, detiene el bucle
				sw = false;
			else {
			
				//Se imprime un mensaje de error indicando el formato esperado para el numero de contrato
				System.out.println("===================================================================================");
				System.err.println("[ERROR] El número de contrato debe tener 7 caracteres y seguir el formato ddd-dddd\n");	
				
					
				//Se vuelve a imprimir el mensaje para pedir el numero de contrato
				System.out.print("Escribir el numero de contrato: ");
				contrato = in.nextLine();
				}
			
		}
		
		//Se retorna el valor de la variable contrato	
		return contrato;
		
	}
	
	/*
	 * El método "pedirPotencias" solicita al usuario que ingrese una de las opciones de potencias disponibles y verifica que la entrada sea válida. 
	 * Si la entrada no es válida, se muestra un mensaje de error y se vuelve a solicitar al usuario que ingrese una opción. 
	 * Una vez que se ha ingresado una opción válida, se devuelve como resultado del método.
	 * */
	public static String pedirPotencias(Scanner in) {
		
		
			// Pedir al usuario que ingrese la potencia contratada
			System.out.println("Escriba una de las opciones abajo.");
			
			// Imprimir las opciones de potencias disponibles
			System.out.println("Potencias: " +
					"\n'3.45'" + "\n'4.60'" + "\n'5.75'" + "\n'6.90'" + "\n'8.05'");
			System.out.print("Potencia contratada? ");
			String p = in.nextLine();
			
			// Validar la entrada del usuario, si no es una de las opciones disponibles, volver a preguntar
			while(!p.contentEquals("3.45")&&!p.contentEquals("4.60")&&!p.contentEquals("5.75")&&!p.contentEquals("6.90")&&!p.contentEquals("8.05")) {
				System.err.println("[ERROR] Escribir con '.' porfavor");
				System.out.print("Potencia contratada? ");
				p = in.nextLine();
			}
			
		//Retorna la potencia contratada
		return p;
	}

	
	/*Este método toma una cadena como entrada y la compara con varios casos para determinar el precio correspondiente a la potencia contratada. Devuelve el precio como un float
	 * */
	public static float potenciaPrecio(String pedirPotencias) {
		float precio = 0;
		
		//Asigna el precio segun la potencia contratada
		switch(pedirPotencias) {
		case "3.45":
			precio = (float)10.23;
			break;
		case "4.60":
			precio = (float)14.45;
			break;
		case "5.75":
			precio = (float)18.69;
			break;
		case "6.90":
			precio = (float)21.34;;
			break;
		case "8.05":
			precio = (float)25.99;
			break;
		default:
			System.err.println("[Error]");
			
	}
		return precio;		
	}
	
	
	/*Este metodo pide al usuario que introduzca los kilovatios consumidos y comprueba que el valor introducido sea mayor o igual a 0.
	 * */
	public static float pedirKw(Scanner in){
		float kw = 0;
		boolean validar = false;
		do {
			// Pide al usuario que introduzca los kW consumidos
			System.out.print("Introducir los kW consumidos: ");
			kw = in.nextFloat();
			
			// Si el valor introducido es menor a 0, muestra un mensaje de error
			if(kw < 0) {
				System.err.println("[ERROR] No se puede introducir valores menor que zero.");
			}
		}while(validar);
		
		return kw;
		
	}

	
	/*El método "calcPrecioKw" calcula el precio de los kW consumidos multiplicando los kW introducidos por el valor del precio por kW establecido (0.1684).
	 * */
	public static float calcPrecioKw(float kw) {
		//calcula el precio por kW consumido multiplicando por el precio por kW establecido
		float precioKw = (float) (kw * 0.1684);
		
		return precioKw;
		
	}
	
	
	/*El método "calcIncremento" calcula el incremento en el precio a partir de la cantidad de kW consumidos, utilizando diferentes tasas de incremento dependiendo de si el consumo se encuentra en un rango específico.
	 * */
	public static float calcIncremento(float kw, float importe) {
		float incremento = 0;
		
		//Se comprueba si los kW consumidos están en un rango específico para aplicar un incremento
		if( kw > 151&& kw < 301) 
			incremento = (float) (importe * 1.05);//Se aplica un incremento del 5%
		else if(kw < 401) 
			incremento = (float) (importe * 1.08);//Se aplica un incremento del 8%
		else 
			incremento = (float) (importe * 1.12);//Se aplica un incremento del 12%
		
		
		return incremento;
		
	}

	
	/*El método calcula el importe sumando el precio de la potencia contratada y el precio de los kW consumidos
	 * */
	public static float calcImporte(float precio, float precioKw) {	
		
		float value = precioKw + precio;		
		return value;		
	}

	
	/*El método informe imprime los datos del cliente, incluyendo el número de contrato, el total de kW consumidos, la potencia contratada, 
	 * el precio final con incremento, el total de kW facturado de todos los clientes y la información se presenta en un formato establecido.*/
	public static void informe(Scanner in, String numContratos, float kwTotal, String potenciaContratada, float importefinal,  float importeBase, float kWContratado) {
		
		float incremento =  importefinal - importeBase;
		
		System.out.println("-----------------------INFORME-------------------------------");
		System.out.println("Numero de contrato: " + numContratos);
        System.out.println("Total kW: " + kWContratado);
        System.out.println("Potencia: " + potenciaContratada);
		System.out.println("Incremento: " + String.format("%.3f", incremento));
        System.out.println("Precio a pagar: " + String.format("%.3f", importefinal) + "€");        
        System.out.println("Total kW Facturado (todos los clientes): " + kwTotal);
        System.out.println("-------------------------------------------------------------");		
	}

	
	/*metodo para agradecer por utilizar el progrma*/
	public static void fin() {
		System.out.println("      ______  _        _          _              __   __");
        System.out.println("     |  ____|| |      | |        (_)             \\ \\ / /");
        System.out.println("     | |__   | |  ___ | |_  _ __  _   ___   __ _  \\ V /");
        System.out.println("     |  __|  | | / _ \\| __|| '__|| | / __| / _` |  > <");
        System.out.println("     | |____ | ||  __/| |_ | |   | || (__ | (_| | / . \\");
        System.out.println("     |______||_| \\___| \\__||_|   |_| \\___| \\__,_|/_/ \\_\\"
        		+ "");

        System.out.println("              +------------------------------+");
        System.out.println("              |Gracias por usar este programa|");
        System.out.println("              +------------------------------+");
        System.out.println("                     Firmado: Charles");
	}
	
	
}

	
	