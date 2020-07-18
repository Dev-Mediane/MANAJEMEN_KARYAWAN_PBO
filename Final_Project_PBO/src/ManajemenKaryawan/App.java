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

        System.out.print("Pilihan menu: "); String option = InputValue.nextLine();
        navigateOptions(option);
    }

    static void interfaceMenu()
    {
        System.out.println("================================   ");
        System.out.println("Employee    Management    System   ");
        System.out.println("    Final Project OOP 19 IT2       ");
        System.out.println("================================ \n");

        System.out.println("--------------------------------   ");
        System.out.println("1 >> Sign In                       ");
        System.out.println("2 >> Log In                        ");
        System.out.println("3 >> Sign Out                      ");
        System.out.println("4 >> About                         ");
        System.out.println("0 >> Exit                          ");
        System.out.println("-------------------------------- \n");
    }

    public static void navigateOptions(String option)
    {
        // Program akan mendeteksi apa pilihan dari user
        if (option.equals("sign in") || option.equals("1")) {
            SignIn(); main(argumentsList);
        }
        else if (option.equals("log in") || option.equals("2")) {
            LogIn(); main(argumentsList);
        }
        else if (option.equals("sign out") || option.equals("3")) {
            SignOut(); main(argumentsList);
        }
        else if (option.equals("about") || option.equals("4")) {
            aboutProgram(); main(argumentsList);
        }
        else if (option.equals("exit") || option.equals("0")) {
            Exit();
        }

        // bila user salah input, akan muncul pesan error
        else
        {
            System.out.println("---------------------------------------------");
            System.out.println("i << Warning                               \n");
            System.out.println("Maaf, input yang diberikan tidak sesuai menu.");

            osSystem_Pause(); System.out.println("---------------------------------------------"); 
            main(argumentsList);
        }
    }

    private static void SignIn()
    {
        // Tujuan Sign In = mendaftarkan sebuah akun ke sistem
        String strNomorID, strPassword;
        System.out.println("=======================================");
        System.out.println("1 >> Sign In \n");

        System.out.println("------------------------------");
        System.out.println("i << INPUT FORMATTING          \n");

        System.out.println("\"01xxx\" = Akun Karyawan");
        System.out.println("\"02xxx\" = Akun Administrator");
        System.out.println("------------------------------ \n");
        osSystem_Pause(); System.out.println();

        // Input data nomorID beserta password (sesuai format di atas)
        System.out.println("-------------------------------");
        System.out.print("Nomor ID (new) = "); strNomorID = InputValue.nextLine();
        System.out.print("Password (new) = "); strPassword = InputValue.nextLine();
        System.out.println("------------------------------- \n");
        
        // Program menanyakan user apakah betul - betul setuju dengan registrasi akun ini atau tidak
        System.out.print("Konfirmasi Sign In? "); String konfirmasi = InputValue.nextLine();
        
        // Bila user TERIMA..
        if (confirmation(konfirmasi))
        {
            // HATI - HATI!!
            // Sebelumnya, nomor ID yang diinput ternyata mempunyai format
            // Format sudah tertera sebelum kita harus menginput
            // Jadi, sebelum registrasi, data nomorID harus dideteksi dulu

            // Mulanya..
            // Bila nomorID berawalan "01", maka user akan dianggap sebagai Employee (Karyawan)
            if (strNomorID.substring(0, 2).equals("01"))
            {
                // Akun akan ditransfer ke Database.java
                dataRegistrasi = new Registrasi(strNomorID.toCharArray(), strPassword.toCharArray());
                Database.append(dataRegistrasi);

                // Akun karyawan akan terbentuk
                dataAkunKaryawan = new AkunKaryawan();
                Database.append(dataAkunKaryawan);

                dataAkunKaryawan.getDataRegistrasi().setNomorID(strNomorID.toCharArray());
                dataAkunKaryawan.getDataRegistrasi().setPassword(strPassword.toCharArray());
                
                System.out.println("STATUS = Employee");
                System.out.println("--------------------------------- \n");
            }
            // Sedangkan..
            // Bila nomorID berawalan "02", maka user akan dianggap sebagai Administrator (Admin)
            else if (strNomorID.substring(0, 2).equals("02"))
            {
                // Akun akan ditransfer ke Database.java
                dataRegistrasi = new Registrasi(strNomorID.toCharArray(), strPassword.toCharArray());
                Database.append(dataRegistrasi);

                // Akun administrator akan terbentuk
                dataAkunAdministator = new AkunAdministrator();
                Database.append(dataAkunAdministator);

                dataAkunAdministator.getDataRegistrasi().setNomorID(strNomorID.toCharArray());
                dataAkunAdministator.getDataRegistrasi().setPassword(strPassword.toCharArray());

                System.out.println("STATUS = Administrator");
                System.out.println("----------------------------------- \n");
            }

            // Akan tetapi..
            // Bila nomorID tidak berawalan "01" maupun "02" (bertentangan dengan format), data tidak bisa terinput
            else if (!strNomorID.substring(0, 2).equals("01") && !strNomorID.substring(0, 2).equals("02"))
            {
                System.out.println("------------------------------------------");
                System.out.println("Maaf, input Anda tidak sesuai format.     ");
                System.out.println("Pastikan data Anda memenuhi syarat Sign In");
                System.out.println("------------------------------------------ \n");
            }
        }

        // Bila user TOLAK atau tidak merespon..
        // data Sign In tidak akan terinput
        else
        {
            System.out.println("---------------------------------------");
            System.out.println("Akun yang diinput BATAL disimpan.      ");
            System.out.println("Silakan mendaftarkan akun baru kembali."); 
            System.out.println("---------------------------------------\n");
        }
        
        osSystem_Pause(); System.out.println();
    }

    private static void LogIn()
    {
        // Tujuan Log in = masuk ke akun yang bersangkutan (yang sudah diregistrasi)
        String strNomorID, strPassword;
        while (true)
        {
            System.out.println("====================");
            System.out.println("2 >> Log In \n");

            // Mula - mula, user menginput nomorID & password seperti biasa
            System.out.print("Nomor ID (existed) = "); strNomorID = InputValue.nextLine();
            System.out.print("Password (existed) = "); strPassword = InputValue.nextLine(); System.out.println();

            // Program menanyakan user apakah betul - betul setuju dengan registrasi akun ini atau tidak
            System.out.print("Konfirmasi Log In? "); String konfirmasi = InputValue.nextLine();

            // Bila user TOLAK atau TIDAK MERESPON..
            // Proses Log In dibatalkan/ ditunda
            if (!confirmation(konfirmasi))
            {
                System.out.println("---------------------------------------  ");
                System.out.println("Log In DIBATALKAN!                       ");
                System.out.println("Silakan kembali ke menu awal.            ");
                System.out.println("---------------------------------------\n");

                osSystem_Pause(); System.out.println(); return; // Kembali ke menu awal
            }

            // Bila user TERIMA..
            else if (confirmation(konfirmasi))
            {
                // Data registrasi (password dan nomorID) harus diperiksa ulang
                // Bila data input mengalami kesalahan, maka user tidak bisa lanjut
                if (!nomorID_isExist(strNomorID) || !password_isExist(strPassword)) 
                {
                    System.out.println("----------------------------------");
                    System.out.println("i >> Warning");
                    System.out.println("Incorrect employee ID or password.");
                    System.out.println("---------------------------------- \n");
                }

                // Akan tetapi..
                // Bila data input SUDAH SESUAI, maka user bisa lanjut
                else if (nomorID_isExist(strNomorID) && password_isExist(strPassword)) 
                {
                    osSystem_Pause(); System.out.println();
                    break;
                }
            }
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
        // Apabila nomor ID berawalan "01", maka user akan diarahkan ke Menu Karyawan
        if (strNomorID.substring(0, 2).equals("01"))
        {
            for (int i = 0; i < Database.accessDaftarKaryawan().size(); i++) 
            {
                char[] nomorID_LinkedList = Database.accessDaftarKaryawan().get(i).getDataRegistrasi().getNomorID();
                
                if (Arrays.equals(nomorID_LinkedList, strNomorID.toCharArray())) {
                    MenuKaryawan.dataAkunKaryawan = Database.accessDaftarKaryawan().get(i);
                    MenuKaryawan.interfaceMenu(); return;
                }
            }
        }
        // Apabila nomor ID berawalan "02", maka user akan diarahkan ke Menu Administrator
        else if (strNomorID.substring(0, 2).equals("02")) 
        {
            for (int i = 0; i < Database.accessDaftarAdministrator().size(); i++)
            {
                char[] nomorID_LinkedList = Database.accessDaftarAdministrator().get(i).getDataRegistrasi().getNomorID();
                
                if (Arrays.equals(nomorID_LinkedList, strNomorID.toCharArray())) {
                    MenuAdministrator.dataAkunAdministrator = Database.accessDaftarAdministrator().get(i);
                    MenuAdministrator.interfaceMenu(); return;
                }
            }
        }
    }

    private static void SignOut()
    {
        // Tujuan Sign Out = menghapus akun yang tidak terpakai
        // Akan tetapi, sebelum Sign Out, pastikan ada minimal 1 akun yang di-Sign In

        // Bila TIDAK ADA akun sama sekali..
        // User tidak akan bisa "Sign Out"

        // Contoh kasus: User secara tidak sengaja klik menu "Sign Out" daripada "Sign In"
        if (Database.accessDaftarRegistrasi().size() == 0)
        {
            System.out.println("---------------------------------------");
            System.out.println("i << Warning                           ");
            System.out.println("Maaf, TIDAK ADA akun yang diregistrasi.");
            System.out.println("--------------------------------------- \n");

            osSystem_Pause(); return;
        }

        // Bila sudah ADA minimal 1 akun yang didaftar..
        else if (Database.accessDaftarRegistrasi().size() > 0)
        {
            boolean dataTerhapus = false;
            String inputNomorIDPenanggungJawab = "", inputPasswordPenanggungJawab = "";
            String inputNomorID = "", inputPassword = "";
            String nomorIDDatabase, passwordDatabase; int indeksTerakhir;
            String nomorIDDalamDatabase, passwordDalamDatabase;
            do
            {
                System.out.println("====================");
                System.out.println("3 >> Sign Out \n");

                // User akan ditanya apakah user akan menghapus akun atau tidak
                System.out.print("Apakah Anda ingin menghapus Akun? "); String konfirmasi = InputValue.nextLine();
                System.out.println();

                // Bila user TOLAK atau TIDAK MERESPON..
                // "Sign Out" tidak jadi dilakukan, dan user kembali ke menu
                if (!confirmation(konfirmasi))
                {
                    System.out.println("-----------------------------");
                    System.out.println("Sign Out DIBATALKAN!         ");
                    System.out.println("Silakan kembali ke menu awal.");
                    System.out.println("----------------------------- \n");

                    osSystem_Pause(); return;
                }
                
                // Bila user TERIMA..
                // "Sign Out" dilanjutkan, dengan syarat..
                else if (confirmation(konfirmasi))
                {
                    // User yang setuju harus ikuti SEMUA ATURAN di bawah, karena..
                    // Proses "Sign Out" ialah proses BERBAHAYA
                    // Setiap kesalahan penulisan data ialah tanggung jawab dari user
                    // Program tidak akan bertanggung jawab bila ada keteledoran dalam bentuk apapun

                    System.out.println("================================================    ");
                    System.out.println("i << Warning! Danger Zone                           ");
                    System.out.println("Akun yang terhapus TIDAK DAPAT dikembalikan.        ");
                    System.out.println("Setiap kesalahan dalam proses Sign Out, seperti:  \n");

                    System.out.println("1. Hapus akun yang salah                            ");
                    System.out.println("2. Hapus akun tanpa pertimbangan yang matang      \n");

                    System.out.println(".. ialah TANGGUNG JAWAB user.                       ");
                    System.out.println("Program tidak bertanggung jawab pada apapun.        ");
                    System.out.println("================================================  \n"); 
                    osSystem_Pause(); System.out.println();

                    // Oleh sebab itu, setiap user yang ingin "Sign Out" WAJIB memberikan identitas..
                    // sebagai bukti bahwasannya "User bertanggung jawab kepada semua risiko yang terjadi"

                    System.out.print("Nomor ID (penanggung jawab) = "); inputNomorIDPenanggungJawab = InputValue.nextLine();
                    System.out.print("Password (penanggung jawab) = "); inputPasswordPenanggungJawab = InputValue.nextLine(); 
                    System.out.println();

                    // Program kemudian akan mengecek ulang,
                    // apakah "user yang bertanggung jawab" ini memiliki identitas yang kuat atau tidak
                    for (int urutanDaftar = 0; urutanDaftar < Database.accessDaftarRegistrasi().size(); urutanDaftar++)
                    {
                        nomorIDDalamDatabase = String.copyValueOf(Database.accessDaftarRegistrasi().get(urutanDaftar).getNomorID());
                        passwordDalamDatabase = String.copyValueOf(Database.accessDaftarRegistrasi().get(urutanDaftar).getPassword());
                        indeksTerakhir = Database.accessDaftarRegistrasi().size() - 1;

                        // Bila user ternyata salah input data..
                        // User harus menginput ulang datanya kembali (mengulang dari awal) 
                        if ((!nomorIDDalamDatabase.equals(inputNomorIDPenanggungJawab) || !passwordDalamDatabase.equals(inputPasswordPenanggungJawab)) && urutanDaftar == indeksTerakhir) {
                            System.out.println("---------------------------------------------  ");
                            System.out.println("Maaf, Anda salah menginput data Anda.          ");
                            System.out.println("Silakan cek data akun Anda sebelum Sign Out.   ");
                            System.out.println("-------------------------------------------- \n"); break;
                        }

                        // Bila data "penanggung jawab" sudah benar..
                        else if (nomorIDDalamDatabase.equals(inputNomorIDPenanggungJawab) && passwordDalamDatabase.equals(inputPasswordPenanggungJawab)) 
                        {    
                            // User akan mengisi data akun mana yang mau dihapus (di-Sign Out)
                            System.out.print("Nomor ID yang ingin dihapus = "); inputNomorID = InputValue.nextLine();
                            System.out.print("Password yang ingin dihapus = "); inputPassword = InputValue.nextLine(); System.out.println();

                            System.out.print("Anda yakin ingin menghapus akun ini? "); String konfirmasiKembali = InputValue.nextLine();
                            System.out.println();

                            if (confirmation(konfirmasiKembali))
                            {
                                for (int i = 0; i < Database.accessDaftarRegistrasi().size(); i++)
                                {
                                    nomorIDDatabase = String.copyValueOf(Database.accessDaftarRegistrasi().get(i).getNomorID());
                                    passwordDatabase = String.copyValueOf(Database.accessDaftarRegistrasi().get(i).getPassword());
                                    indeksTerakhir = Database.accessDaftarRegistrasi().size() - 1;

                                    // Kalau data sudah teregistrasi, maka user dapat menghapus akun yang bersangkutan
                                    if (nomorIDDatabase.equals(inputNomorID) && passwordDatabase.equals(inputPassword)) 
                                    {
                                        // Bila akun yang terhapus berstatus "Karyawan", data Karyawan terkait juga akan ikut terhapus
                                        if (inputNomorID.substring(0, 2).equals("01"))
                                        {
                                            for (int j = 0; j < Database.accessDaftarKaryawan().size(); j++)
                                            {
                                                nomorIDDatabase = String.copyValueOf(Database.accessDaftarKaryawan().get(j).getDataRegistrasi().getNomorID());
                                                passwordDatabase = String.copyValueOf(Database.accessDaftarKaryawan().get(j).getDataRegistrasi().getPassword());

                                                if (nomorIDDatabase.equals(inputNomorID) && passwordDatabase.equals(inputPassword))
                                                {
                                                    Database.accessDaftarRegistrasi().remove(i);
                                                    Database.accessDaftarKaryawan().remove(j);
                                                    dataTerhapus = true; break;
                                                }
                                            }
                                        }

                                        // Bila akun tersebut berstatus "Administrator", data Admin terkait juga akan ikut terhapus
                                        else if (inputNomorID.substring(0, 2).equals("02"))
                                        {
                                            for (int k = 0; k < Database.accessDaftarAdministrator().size(); k++)
                                            {
                                                nomorIDDatabase = String.copyValueOf(Database.accessDaftarAdministrator().get(k).getDataRegistrasi().getNomorID());
                                                passwordDatabase = String.copyValueOf(Database.accessDaftarAdministrator().get(k).getDataRegistrasi().getPassword());

                                                if (nomorIDDatabase.equals(inputNomorID) && passwordDatabase.equals(inputPassword))
                                                {
                                                    Database.accessDaftarRegistrasi().remove(i);
                                                    Database.accessDaftarAdministrator().remove(k);
                                                    dataTerhapus = true; break;
                                                }
                                            }
                                        }
                                        break;
                                    }

                                    // Kalau tidak ada, Sign Out tidak jadi dilakukan
                                    else if ((!nomorIDDatabase.equals(inputNomorID) || !passwordDatabase.equals(inputPassword)) && i == indeksTerakhir)
                                    {
                                        System.out.println("-------------------------------------------   ");
                                        System.out.println("Maaf, Akun Anda tidak ditemukan!              ");
                                        System.out.println("Silakan cek nomorID dan password akun Anda.   ");
                                        System.out.println("------------------------------------------- \n");
                                    }
                                }
                            }
                            else if (!confirmation(konfirmasiKembali))
                            {
                                System.out.println("----------------------------------------");
                                System.out.println("Sign Out akun DIBATALKAN                ");
                                System.out.println("Silakan mulai proses Sign Out dari awal.");
                                System.out.println("---------------------------------------- \n");
                            }
                        }
                    }
                }  
            } while (dataTerhapus == false);

            System.out.println("----------------------------------------------------   ");
            System.out.println("i << Sign Out Completed                              \n");

            System.out.println(inputNomorID + " telah DIHAPUS dari program.            ");
            System.out.println("Penanggung Jawab = " + inputNomorIDPenanggungJawab);
            System.out.println("---------------------------------------------------- \n");

            osSystem_Pause();
        }
    }

    private static void aboutProgram()
    {
        do
        {
            // Menu ini hanya menampilkan credit (catatan kaki, terima kasih, dsb.)
            System.out.println("====================");
            System.out.println("5 >> About \n");

            System.out.println("--------------------");
            System.out.println("a. Deskripsi Program");
            System.out.println("b. Kontribusi");
            System.out.println("c. Terima Kasih");
            System.out.println("-------------------- \n");
            System.out.print("Pilihan = "); String pilihan = InputValue.nextLine(); System.out.println();

            if (pilihan.equals("a") || pilihan.equals("A"))
            {
                System.out.println("a >> Deskripsi Program");
                System.out.println("====================== \n");

                System.out.println("Latar Belakang");
                System.out.println("-------------- \n");

                System.out.println("Employee Management System ialah Software yang digunakan dalam dunia kerja.");
                System.out.println("Sesuai namanya, Employee Management System hadir untuk memenuhi kebutuhan kantor, terutama karyawan");
                System.out.println(); osSystem_Pause();
            }
            else if (pilihan.equals("b") || pilihan.equals("B"))
            {
                System.out.println("b >> Kontributor ");
                System.out.println("================ \n");

                System.out.print("1. Albert Cenderawan   (03082190015) 19IT2 Universitas Pelita Harapan Medan "); InputValue.nextLine();
                System.out.print("2. Harris Siaputra     (03082190009) 19IT2 Universitas Pelita Harapan Medan "); InputValue.nextLine();
                System.out.print("3. Hubert Daniel Rusli (03082190024) 19IT2 Universitas Pelita Harapan Medan "); InputValue.nextLine();
                System.out.print("4. Wilbert Khosasi     (03082190019) 19IT2 Universitas Pelita Harapan Medan "); InputValue.nextLine();
                System.out.println(); osSystem_Pause();
            }
            else if (pilihan.equals("c") || pilihan.equals("C"))
            {
                System.out.println("c >> Terima Kasih");
                System.out.println("================= \n");

                System.out.print("1. Ade Maulana (Object Oriented Programming Lecturer) Universitas Pelita Harapan Medan "); InputValue.nextLine();
                System.out.print("2. GitHub (WilbertK1, HarrisSiaputra, Dev-Mediane) "); InputValue.nextLine();
                System.out.println(); osSystem_Pause();
            }
            else
            {
                System.out.print("Anda ingin keluar ke menu? "); String konfirmasi = InputValue.nextLine();
                if (confirmation(konfirmasi)) {
                    break;
                }
            }
        }
        while (true);
    }

    private static void Exit()
    {
        // Belum Selesai (by 03082190015)
        System.out.println("====================");
        System.out.println("0 >> Exit \n");

        System.out.println("--------------------------------------------------  ");
        System.out.println("WARNING >> DANGER ZONE                              ");
        System.out.println("Semua data akan terhapus bila Anda KELUAR program.  ");
        System.out.println("--------------------------------------------------\n");

        System.out.print("Apakah Anda ingin keluar dari program ini? "); String konfirmasi = InputValue.nextLine();
        System.out.println();

        if (confirmation(konfirmasi))
        {
            Database.resetDaftarAdmin();
            Database.resetDaftarKaryawan();
            Database.resetDaftarRegistrasi();

            System.out.println("=============================================");
            System.out.println("Terima Kasih telah menggunakan program ini!  ");
            System.out.println("Have a Good Day! -- @EmployeeManagementSystem");
            System.out.println("============================================= \n");
        }
        else {
            main(argumentsList);
        }
    }

    public static void osSystem_Pause()
    {
        System.out.print("Please select any key to continue... "); 
        InputValue.nextLine();
    }

    public static boolean confirmation(String inputKonfirmasi)
    {
        String[] agree = {"ya", "iya", "y", "yes", "yoi", "yeah"};
        String[] disagree = {"tidak", "no", "t", "n", "nggak", "nope", "ngga", "not", "x", "g"};

        for (int i = 0; i < agree.length; i++) {
            if (inputKonfirmasi.toLowerCase().equals(agree[i])) {
                return true;
            }
        }

        for (int j = 0; j < disagree.length; j++) {
            if (inputKonfirmasi.toLowerCase().equals(disagree[j])) {
                return false;
            }
        }

        return false;
    }
}