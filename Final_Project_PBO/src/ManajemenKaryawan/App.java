package ManajemenKaryawan;
import java.util.Arrays;
import java.util.Scanner;
import ManajemenKaryawan.*;

/**
 * @author
 * Albert Cenderawan, Harris Siaputra, Hubert Daniel Rusli, Wilbert Khosasi
 * 
 * @category
 * Aplikasi utama untuk "Manajemen Karyawan"
 */

public class App {
    static Registrasi dataRegistrasi;
    static AkunKaryawan dataAkunKaryawan;
    static AkunAdministrator dataAkunAdministator;
    static String[] argumentsList = {"1", "2", "3"};
    static Scanner InputValue = new Scanner(System.in);

    public static void main(String[] args)
    { 
        interfaceMenu();

        System.out.print("Pilihan menu: "); String pilihan = InputValue.nextLine();
        String option = pilihan.toString(); navigateOptions(option);
    }

    static void interfaceMenu()
    {
        System.out.println("==========================");
        System.out.println("Employee Management System");
        System.out.println("Final Project OOP 19IT2   ");
        System.out.println("========================== \n");

        System.out.println("--------------------------   ");
        System.out.println("1 >> Sign In                 ");
        System.out.println("2 >> Log In                  ");
        System.out.println("3 >> Sign Out                ");
        System.out.println("4 >> Feedback                ");
        System.out.println("5 >> About                   ");
        System.out.println("0 >> Exit                    ");
        System.out.println("-------------------------- \n");
    }

    public static void navigateOptions(String option)
    {
        String[] optionsList = new String[10];
        optionsList[0] = "sign in";     // daftar akun baru
        optionsList[1] = "log in";      // masuk ke akun tertentu
        optionsList[2] = "sign out";    // hapus sebuah akun
        optionsList[3] = "feedback";    // beri saran dan aspirasi
        optionsList[4] = "about";       // informasi program
        optionsList[5] = "exit";        // keluar dari program

        if (option.equals(optionsList[0]) || option.equals("1"))
        {
            SignIn();
        }
        else if (option.equals(optionsList[1]) || option.equals("2"))
        {
            LogIn();
        }
        else if (option.equals(optionsList[2]) || option.equals("3"))
        {
            SignOut();
        }
        else if (option.equals(optionsList[3]) || option.equals("4"))
        {
            Feedback();
        }
        else if (option.equals(optionsList[4]) || option.equals("5"))
        {
            aboutProgram();
        }
        else if (option.equals(optionsList[5]) || option.equals("0"))
        {
            Exit();
        }
    }

    private static void SignIn()
    {
        // Belum Selesai (by 03082190015)
        String strNomorID = " ", strPassword = " ", strConfirmPassword = " ";
        System.out.println("=======================================");
        System.out.println("1 >> Sign In \n");

        while (true)
        {
            System.out.print("Employee ID = "); strNomorID = InputValue.nextLine();
            System.out.print("Password    = "); strPassword = InputValue.nextLine();
            System.out.print("Confirm Password = "); strConfirmPassword = InputValue.nextLine(); System.out.println();

            if (!strConfirmPassword.equals(strPassword)) {
                System.out.println("Maaf, data Anda mengalami kesalahan.");
                System.out.println("Silakan input data Anda kembali. \n");
                
            }
            else if (strConfirmPassword.equals(strPassword)) {

                System.out.print("Ingin menambah akun baru? "); String konfirmasi = InputValue.nextLine(); System.out.println();
                if (konfirmasi.equals("ya") || konfirmasi.equals("yes"))
                {
                    dataRegistrasi = new Registrasi(strNomorID.toCharArray(), strPassword.toCharArray());
                    Database.append(dataRegistrasi);
                    break;
                }
                else if (konfirmasi.equals("tidak") || konfirmasi.equals("no"))
                {
                    System.out.println("---------------------------------------");
                    System.out.println("i >> Cancelled");
                    System.out.println("Akun yang diinput BATAL disimpan.");
                    System.out.println("Silakan mendaftarkan akun baru kembali."); 
                    System.out.println("--------------------------------------- \n");
                    osSystem_Pause(); main(argumentsList); break;
                }
            }
        }

        if (strNomorID.substring(0, 2).equals("01")) // diinput sebagai karyawan
        {
            dataAkunKaryawan = new AkunKaryawan();      // membuat objek karyawan
            Database.append(dataAkunKaryawan);          // memasukkan data ke database

            dataAkunKaryawan.getDataRegistrasi().setNomorID(strNomorID.toCharArray());
            dataAkunKaryawan.getDataRegistrasi().setPassword(strPassword.toCharArray());
            
            System.out.println("---------------------------------");
            System.out.println("i >> Received");
            System.out.println("Status Anda = EMPLOYEE (Karyawan)");
            System.out.println("--------------------------------- \n");
        }
        else if (strNomorID.substring(0, 2).equals("02")) // diinput sebagai administrator
        {
            dataAkunAdministator = new AkunAdministrator();
            Database.append(dataAkunAdministator);

            dataAkunAdministator.getDataRegistrasi().setNomorID(strNomorID.toCharArray());
            dataAkunAdministator.getDataRegistrasi().setPassword(strPassword.toCharArray());

            System.out.println("-----------------------------------");
            System.out.println("i >> Received");
            System.out.println("Status Anda = ADMINISTRATOR (Admin)");
            System.out.println("----------------------------------- \n");
        }

        osSystem_Pause();
        System.out.println("-------------------------");
        System.out.println("Data Anda telah terkirim.");
        System.out.println("Silakan Log In akun Anda.");
        System.out.println("------------------------- \n");

        osSystem_Pause();
        System.out.println("========================= \n");

        main(argumentsList);
    }

    private static void LogIn()
    {
        // Belum Selesai (by 03082190015)
        String strNomorID, strPassword;

        while (true)
        {
            System.out.println("====================");
            System.out.println("2 >> Log In \n");

            System.out.print("Employee ID = "); strNomorID = InputValue.nextLine();
            System.out.print("Password    = "); strPassword = InputValue.nextLine();

            if (!nomorID_isExist(strNomorID) || !password_isExist(strPassword)) 
            {
                System.out.println("----------------------------------");
                System.out.println("i >> Warning");
                System.out.println("Incorrect employee ID or password.");
                System.out.println("---------------------------------- \n");
            }

            if (nomorID_isExist(strNomorID) && password_isExist(strPassword)) 
            {
                System.out.println("=================================== \n");
                osSystem_Pause(); break;
            }
            System.out.println();
        }

        // arahkan user, apakah ia masuk ke MenuKaryawan.java OR MenuAdministrator.java
        navigateAccount(strNomorID);
    }

    private static boolean nomorID_isExist(String strNomorID)
    {
        // Mengecek apakah nomorID yang diinput sudah ada di LinkedList atau tidak (by 03082190015)
        for (int urutanDaftar = 0; urutanDaftar < Database.accessDaftarRegistrasi().size(); urutanDaftar++)
        {
            // nomorID_LinkedList = nomorID yang ada dalam LinkedList NomorID
            // misalnya:    bila i = 0, maka nomorID_LinkedList = nomor ID urutan ke-0
            char[] nomorID_LinkedList = Database.accessDaftarRegistrasi().get(urutanDaftar).getNomorID();

            // Arrays.equals = membandingkan 2 char array
            if (Arrays.equals(nomorID_LinkedList, strNomorID.toCharArray())) {return true;}
        }
        return false;
    }

    private static boolean password_isExist(String strPassword)
    {
        // Mengecek apakah password yang diinput sudah ada di LinkedList atau tidak (by 03082190015)
        for (int urutanDaftar = 0; urutanDaftar < Database.accessDaftarRegistrasi().size(); urutanDaftar++)
        {
            // password_LinkedList = password yang ada dalam LinkedList Password
            // misalnya:    bila i = 0, maka password_LinkedList = password urutan ke-0
            char[] password_LinkedList = Database.accessDaftarRegistrasi().get(urutanDaftar).getPassword();
            
            // Arrays.equals = membandingkan 2 char array
            if (Arrays.equals(password_LinkedList, strPassword.toCharArray())) {return true;}
        }
        return false;
    }

    private static void navigateAccount(String strNomorID)
    {
        if (strNomorID.substring(0, 2).equals("01"))
        {
            for (int i = 0; i < Database.accessDaftarKaryawan().size(); i++) 
            {
                char[] nomorID_LinkedList = Database.accessDaftarRegistrasi().get(i).getNomorID();
                
                if (Arrays.equals(nomorID_LinkedList, strNomorID.toCharArray())) {
                    MenuKaryawan.dataAkunKaryawan = Database.accessDaftarKaryawan().get(i);
                    MenuKaryawan.interfaceMenu();
                }
            }
        }
        else if (strNomorID.substring(0, 2).equals("02")) 
        {
            for (int i = 0; i < Database.accessDaftarAdministrator().size(); i++)
            {
                char[] nomorID_LinkedList = Database.accessDaftarRegistrasi().get(i).getNomorID();
                
                if (Arrays.equals(nomorID_LinkedList, strNomorID.toCharArray())) {
                    MenuAdministrator.dataAkunAdministrator = Database.accessDaftarAdministrator().get(i);
                    MenuAdministrator.interfaceMenu();
                }
            }
        }
    }

    private static void SignOut()
    {
        // Belum Selesai (by 03082190015)
        System.out.println("====================");
        System.out.println("3 >> Sign Out \n");

        osSystem_Pause();
        System.out.println("==================== \n");
        main(argumentsList);
    }

    private static void Feedback()
    {
        // Belum Selesai (by 03082190015)
        System.out.println("====================");
        System.out.println("4 >> Feedback \n");

        osSystem_Pause();
        System.out.println("==================== \n");
        main(argumentsList);
    }

    private static void aboutProgram()
    {
        // Belum Selesai (by 03082190015)

        System.out.println("====================");
        System.out.println("5 >> About \n");

        osSystem_Pause();
        System.out.println("==================== \n");
        main(argumentsList);
    }

    private static void Exit()
    {
        // Belum Selesai (by 03082190015)
        System.out.println("====================");
        System.out.println("0 >> Exit \n");

        System.out.println("------------------------------------------");
        System.out.println("WARNING >> DANGER ZONE                    ");
        System.out.println("Semua data akan terhapus bila Anda keluar.");
        System.out.println("Tentukan konfirmasi Anda saat ini.        \n");
        System.out.println("------------------------------------------");

        System.out.print("Apakah Anda ingin keluar dari program ini? "); String konfirmasi = InputValue.nextLine();
        System.out.println();

        if (konfirmasi.equals("ya") || konfirmasi.equals("yes"))
        {
            Database.resetDaftarAdmin();
            Database.resetDaftarKaryawan();
            Database.resetDaftarRegistrasi();
            System.out.println("====================================== \n");
        }
        else
        {
            osSystem_Pause(); System.out.println("====================================== \n");
            main(argumentsList);
        }
    }

    public static void osSystem_Pause()
    {
        System.out.print("Please select any key to continue... "); 
        InputValue.nextLine(); System.out.println();
    }
}