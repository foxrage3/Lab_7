import java.util.Scanner;
import java.io.*;

public class Lab7Var12 {
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("count");
        int count = sc.nextInt();
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        FileOutputStream fos2 = null;
        ObjectOutputStream oos2 = null;
        FileInputStream fis = null;
        ObjectInputStream oin = null;
        try {
            File f = new File("C:\\Java\\riversIn.txt");
            f.createNewFile();
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            sc.nextLine();
            for (int i = 0; i < count; i++) {
                River river = new River();
                System.out.println("name");
                river.name = sc.nextLine();
                System.out.println("location");
                river.location = sc.nextLine();
                System.out.println("length");
                river.length = sc.nextInt();
                sc.nextLine();
                oos.writeObject(river);
            }
            fis = new FileInputStream(f);
            oin = new ObjectInputStream(fis);
            River riverWrite = null;

            File f2 = new File("C:\\Java\\riversOut.txt");
            fos2 = new FileOutputStream(f2);
            oos2 = new ObjectOutputStream(fos2);
            int lengthRiverMax = -1;
            for (int i = 0; i < count; i++) {
                riverWrite = (River)oin.readObject();
                if(riverWrite.length>lengthRiverMax){
                    lengthRiverMax = riverWrite.length;
                }

            }
            System.out.println(lengthRiverMax);
            for (int i = 0; i < count; i++) {
                riverWrite = (River)oin.readObject();
                if(riverWrite.length==lengthRiverMax){
                    oos2.writeObject(riverWrite);
                    System.out.println(riverWrite);
                }

            }

        }
        catch (EOFException eof){

        }
        catch (ClassNotFoundException ne){

        }
        catch (IOException ie){
            ie.printStackTrace();
        }

        finally {
            oos.flush();
            oos.close();
            fos.close();
            oos2.flush();
            oos2.close();
            fos2.close();
            oin.close();
            fis.close();
        }
    }

}
