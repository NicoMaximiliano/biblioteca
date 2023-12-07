package biblioteca;

import java.util.*;
import java.util.regex.*;

public class MenuBiblioteca {
    
    private List<Libro> libros = new ArrayList<Libro>();
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    
    public void mostrarMenu(){
        Scanner entrada = new Scanner(System.in);
        String opc;
        
        do{
            System.out.println("\n\t:::MENU PRINCIPAL:::");
            System.out.println("1. Entrar en la biblioteca");
            System.out.println("2. Registrarse en la biblioteca");
            System.out.println("3. Terminar programa");
            System.out.println("Seleccionar opcion: ");
            opc = entrada.next();

            switch(opc){
                case "1":
                    entrarAlSistema();
                    break;
                case "2":
                    registrarseEnElSistema();
                    break; 
                case "3":
                    System.out.println("\nSaliendo del sistema!!!");
                    break;
                default:
                    System.out.println("\nOpcion incorrecta!!!");
                       
            }
        }while(!(opc.equalsIgnoreCase("3")));

    }
    
    public void entrarAlSistema(){
        System.out.println("\n\t:::ENTRAR AL SISTEMA:::");
        Scanner entradaCorreo = new Scanner(System.in);
        Scanner entradaContrasenia = new Scanner(System.in);
        
        String correo, contrasenia;

        System.out.println("Ingresa tu correo electronico: ");
        correo = entradaCorreo.nextLine();

        System.out.println("Ingrese su contraseña: ");
        contrasenia = entradaContrasenia.next();

        if(validarIngreso(correo, contrasenia)){
            mostrarSubmenuLibros();
        }
        else{
            System.out.println("\nUsuario no registrado, debe registrarse!!!");
        }
     
    }
    
    public boolean validarIngreso(String correo, String contrasenia){
        boolean esCorrecto = false;
        
        for(Usuario usuario: this.usuarios){
            if(correo.equalsIgnoreCase(usuario.getCorreo()) && contrasenia.equalsIgnoreCase(usuario.getContrasenia())){
                return true;
            }
        }
        
        return esCorrecto;
    }
    
    public void registrarseEnElSistema(){
        System.out.println("\n\t:::REGISTRANDOSE EN EL SISTEMA:::");
        Scanner entradaNombre = new Scanner(System.in);
        Scanner entradaApellido = new Scanner(System.in);
        Scanner entradaCorreo = new Scanner(System.in);
        Scanner entradaContrasenia = new Scanner(System.in);
        
        String nombre, apellido, correo, contrasenia;
        Usuario usuario;

        System.out.println("Ingrese su nombre: ");
        nombre = entradaNombre.nextLine();

        System.out.println("Ingrese su apellido: ");
        apellido = entradaApellido.next();

        System.out.println("Ingrese su correo electronico: ");
        correo = entradaCorreo.next();
        correo = validarCorreo(correo);

        System.out.println("Ingrese su contraseña: ");
        contrasenia = entradaContrasenia.next();

        if(validarUsuario(nombre,apellido,correo,contrasenia)){
            usuario = new Usuario(nombre, apellido, correo, contrasenia);
        
            usuarios.add(usuario);

            System.out.println("\nUsuario registrado!!!");
        }
        else{
            System.out.println("\nHa ingresado datos incorrectos.");
            System.out.println("Intentelo de nuevo!!!");
        }
      
    }
    
    public String validarCorreo(String correo){
        //String regx = "^[A-Za-z0-9+_.-]+@+[A-Za-z]?.com$";
        String regx = "^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(correo);
        
        if(matcher.matches()){
            return correo;
        }
        else{
            return null;
        }
    }
    
    public boolean validarUsuario(String nombre, String apellido, String correo, String contrasenia){
        
        if(nombre != null && apellido != null && correo != null && contrasenia != null){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void mostrarSubmenuLibros(){
        Scanner entrada = new Scanner(System.in);
        String opc;
        
        do{
            System.out.println("\n\t:::MENU LIBROS:::");
            System.out.println("1. Agregar libro");
            System.out.println("2. Eliminar libro");
            System.out.println("3. Buscar libro");
            System.out.println("4. Alquilar libro");
            System.out.println("5. Devolver libro");
            System.out.println("6. Mostrar lista de libros");
            System.out.println("7. Salir");
            System.out.println("Seleccionar opcion: ");
            opc = entrada.next();
       
            switch(opc){
                case "1":
                    agregarLibro();
                    break;
                case "2":
                    eliminarLibro();
                    break;
                case "3":
                     buscarLibro();
                    break;
                case "4":
                     alquilarLibro();
                    break;
                case "5":
                     devolverLibro();
                    break;
                case "6":
                     mostrarListaDeLibros();
                    break;    
                case "7":
                    System.out.println("\nSaliendo!!!");
                    break;
                default:
                    System.out.println("\nOpcion incorrecta!!!");
            }
        }while(!(opc.equalsIgnoreCase("7")));

    }
    
    public void agregarLibro(){
        System.out.println("\n\t:::AGREGANDO LIBRO:::");
        Scanner entradaTitulo = new Scanner(System.in);
        Scanner entradaAutor = new Scanner(System.in);
        Scanner entradaGenero = new Scanner(System.in);
        String titulo, autor, genero;
        Libro libro;
        
        System.out.println("Ingresa el titulo del libro: ");
        titulo = entradaTitulo.nextLine();
        
        System.out.println("Ingresa el autor del libro: ");
        autor = entradaAutor.nextLine();
        
        System.out.println("Ingresa el genero del libro: ");
        genero = entradaGenero.next();
        
        libro = new Libro(titulo,autor,genero);
        
        libros.add(libro);

    }
     
    public void eliminarLibro(){
        System.out.println("\n\t:::ELIMINANDO LIBRO:::");
        Scanner entrada = new Scanner(System.in);
        String titulo=null;
        boolean encontrado = false;
      
        System.out.println("Ingresa el titulo del libro: ");
        titulo = entrada.nextLine();
        
        for(Libro libro: libros){
            if(libro.getTitulo().equalsIgnoreCase(titulo)){
                if(libro.isAlquilado()){
                    System.out.println("\nEl libro no se puede eliminar porque esta alquilado!!!");
                }
                else{
                    libros.remove(libro);
                    System.out.println("\nLibro eliminado!!!");
                }
                
                encontrado = false;
                break;
            }
            else{
                encontrado = true;
            }
        }

        if(encontrado){
            System.out.println("\nEl libro no fue encontrado!!!");
        }
        
    }
    
    public void buscarLibro(){
        System.out.println("\n\t:::BUSCANDO LIBRO:::");
        Scanner entrada = new Scanner(System.in);
        Scanner entradaTitulo = new Scanner(System.in);
        Scanner entradaAutor = new Scanner(System.in);
        Scanner entradaGenero = new Scanner(System.in);
        
        String titulo=null, autor=null, genero=null;
        boolean encontrado = false;
        String opc;
        
        
        System.out.println("1. Buscar por titulo");
        System.out.println("2. Buscar por autor");
        System.out.println("3. Buscar por genero");
        System.out.println("Seleccionar opcion: ");
        opc = entrada.next();
        
        
        if(opc.equalsIgnoreCase("1")){
            System.out.println("\nIngresa el titulo del libro: ");
            titulo = entradaTitulo.nextLine();
        }
        else if(opc.equalsIgnoreCase("2")){
            System.out.println("\nIngresa el autor del libro: ");
            autor = entradaAutor.nextLine();
        }
        else if(opc.equalsIgnoreCase("3")){
            System.out.println("\nIngresa el genero del libro: ");
            genero = entradaGenero.next();
        }
        else{
            System.out.println("\nOpcion incorrecta!!!");
        }
        
        System.out.println();
        
        for(Libro libro: libros){
            if(opc.equalsIgnoreCase("1")){
                if(libro.getTitulo().equalsIgnoreCase(titulo)){
                    System.out.println(libro);
                    encontrado = false;
                }
                else{
                    encontrado = true;
                }
            }
            if(opc.equalsIgnoreCase("2")){
                if(libro.getAutor().equalsIgnoreCase(autor)){
                    System.out.println(libro);
                    encontrado = false;
                }
                else{
                    encontrado = true;
                }
            }
            if(opc.equalsIgnoreCase("3")){
                if(libro.getGenero().equalsIgnoreCase(genero)){
                    System.out.println(libro);
                    encontrado = false;
                }
                else{
                    encontrado = true;
                }
            }
        }
 
        if(encontrado){
            System.out.println("\nEl libro no fue encontrado!!!"); 
        }
    }
    
    public void alquilarLibro(){
        System.out.println("\n\t:::ALQUILAR LIBRO:::");
        Scanner entrada = new Scanner(System.in);
        String titulo;
        boolean encontrado = false;
        
        System.out.println("Ingresar el titulo del libro: ");
        titulo = entrada.nextLine();
        
        for(Libro libro: libros){
            if(libro.getTitulo().equalsIgnoreCase(titulo)){
                if(libro.isAlquilado()){
                    System.out.println("\nEl libro no se puede alquilar, ya esta alquilado!!!");
                }
                else{
                    libro.setAlquilado(true);
                    System.out.println("\nLibro alquilado!!!");
                }
                
                encontrado = false;
                break;
            }
            else{
                encontrado = true;
            }
        }
        
        
        if(encontrado){
            System.out.println("\nEl libro no fue encontrado!!!");
        }
 
    }
    
    public void devolverLibro(){
        System.out.println("\n\t:::DEVOLVER LIBRO:::");
        Scanner entrada = new Scanner(System.in);
        boolean encontrado = false;
        String titulo;
        
        System.out.println("Ingresar el titulo del libro: ");
        titulo = entrada.nextLine();
        
        for(Libro libro: libros){
            if(libro.getTitulo().equalsIgnoreCase(titulo)){
                if(libro.isAlquilado()){
                    libro.setAlquilado(false);
                    System.out.println("\nLibro devuelto!!!");
                    break;
                }
            }
            else{
                encontrado = true;
            }
        }
        
        if(encontrado){
            System.out.println("\nEl libro no pertenece a la biblioteca!!!");
        }
    }
    
    public void mostrarListaDeLibros(){
        System.out.println("\n\t:::LISTA DE LIBROS:::");
        
        if(libros.isEmpty()){
            System.out.println("\nLa lista esta vacia!!!");
        }
        else{           
            for(Libro libro: libros){
                System.out.println(libro);
            }
        }

    }

    
}
