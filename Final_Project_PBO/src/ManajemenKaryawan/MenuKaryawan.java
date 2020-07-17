package ManajemenKaryawan;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import ManajemenKaryawan.AkunKaryawan;
import ManajemenKaryawan.App;

public class MenuKaryawan 
{
    /**
     * cuma gabungkan ini ke file AkunKaryawan 
     */
    static AkunKaryawan dataAkunKaryawan;
    static Scanner InputValue = new Scanner(System.in);

    static void interfaceMenu()
    {
        System.out.println("------------------------------  ");
        System.out.println("1 >> Input Identitas            ");
        System.out.println("2 >> Input Jam Kerja            ");
        System.out.println("3 >> Hitung Gaji                ");
        System.out.println("4 >> Ubah Identitas             ");
        System.out.println("5 >> Ubah Jam Kerja             ");
        System.out.println("6 >> Tampilkan Identitas        ");
        System.out.println("7 >> Tampilkan Jam Kerja        ");
        System.out.println("0 >> Log Out                    ");
        System.out.println("------------------------------\n");

        System.out.print("Pilihan Menu: "); String option = InputValue.nextLine();
        navigateOptions(option);
    }

    static void navigateOptions(String pilihan)
    {
        String[] optionsList = new String[10];
        optionsList[0] = "input identitas";
        optionsList[1] = "input jam kerja";
        optionsList[2] = "hitung gaji";
        optionsList[3] = "ubah identitas";
        optionsList[4] = "ubah jam kerja";
        optionsList[5] = "tampilkan identitas";
        optionsList[6] = "tampilkan jam kerja";
        optionsList[7] = "log out";

        if (pilihan.equals(optionsList[0]) || pilihan.equals("1"))
        {
            inputIdentitas();
            interfaceMenu();
        }

        if (pilihan.equals(optionsList[1]) || pilihan.equals("2"))
        {
            inputJamKerja();
            interfaceMenu();
        }
        
        if (pilihan.equals(optionsList[2]) || pilihan.equals("3"))
        {
            hitungGaji();
            interfaceMenu();
        }
        
        if (pilihan.equals(optionsList[3]) || pilihan.equals("4"))
        {
            ubahIdentitas();
            interfaceMenu();
        }
        
        if (pilihan.equals(optionsList[4]) || pilihan.equals("5"))
        {
            ubahJamKerja();
            interfaceMenu();
        }
        
        if (pilihan.equals(optionsList[5]) || pilihan.equals("6"))
        {
            tampilkanIdentitas();
            interfaceMenu();
        }
        
        if (pilihan.equals(optionsList[6]) || pilihan.equals("7"))
        {
            tampilkanJamKerja();
            interfaceMenu();
        }
        
        if (pilihan.equals(optionsList[7]) || pilihan.equals("0"))
        {
            LogOut();
        }
    }

    static void inputIdentitas()
    {
        String inputNamaLengkap, inputNomorHP, inputEmail, inputKendaraan;
        System.out.println("1 >> Input Identitas");
        System.out.println("-------------------- \n");

        System.out.print("Nama Lengkap  = "); inputNamaLengkap = InputValue.nextLine();
        System.out.print("Nomor HP      = "); inputNomorHP = InputValue.nextLine();
        System.out.print("E-mail        = "); inputEmail = InputValue.nextLine(); 
        System.out.print("Kendaraan     = "); inputKendaraan = InputValue.nextLine(); System.out.println();

        dataAkunKaryawan.setDataIdentitasKaryawan(new IdentitasKaryawan(inputNamaLengkap, inputNomorHP, inputEmail, inputKendaraan));
        App.osSystem_Pause();
    }

    static void hitungGaji()
    {
        return dataPenghasilanKaryawan;
    }

    static void ubahIdentitas()
    {
        boolean perubahanIdentitas = false;
        do
        {
            System.out.println("4 >> Ubah Identitas");
            System.out.println("------------------- \n");
            
            System.out.println("===================");
            System.out.println("1. Nama Lengkap    ");
            System.out.println("2. Nomor HP        ");
            System.out.println("3. Alamat E-mail   ");
            System.out.println("=================== \n");

            System.out.print("Bagian yang ingin diubah = "); int pilihan = InputValue.nextInt(); InputValue.nextLine();
            System.out.println();
            
            switch (pilihan)
            {
                case 1: // Hanya ubah nama Lengkap saja
                    System.out.println("Nama Lengkap (old) = " + dataAkunKaryawan.getDataIdentitasKaryawan().getnamaLengkap());
                    System.out.print("Nama Lengkap (new) = "); String inputNamaLengkapBaru = InputValue.nextLine();

                    dataAkunKaryawan.getDataIdentitasKaryawan().setnamaLengkap(inputNamaLengkapBaru);
                    perubahanIdentitas = true; break;

                case 2: // Hanya ubah nomor HP saja
                    System.out.println("Nomor HP (lama) = " + String.copyValueOf(dataAkunKaryawan.getDataIdentitasKaryawan().getNomorHP()));
                    System.out.print("Nomor HP (baru) = "); String inputNomorHPBaru = InputValue.nextLine();

                    dataAkunKaryawan.getDataIdentitasKaryawan().setnomorHP(inputNomorHPBaru);
                    perubahanIdentitas = true; break;

                case 3: // Hanya ubah alamat e-mail saja
                    System.out.println("Alamat e-mail (old) = " + dataAkunKaryawan.getDataIdentitasKaryawan().getemail());
                    System.out.print("Alamat e-mail (new) = "); String inputEmailBaru = InputValue.nextLine();

                    dataAkunKaryawan.getDataIdentitasKaryawan().setemail(inputEmailBaru);
                    perubahanIdentitas = true; break;

                default:
                    System.out.println("Pilihan yang Anda input tidak tertera.");
                    System.out.println("Pilihan hanya dari 1 - 3");
            }
        } while (perubahanIdentitas == false);
    }

    static void inputJamKerja()
    {
        try
        {
            // SimpleDateFormat.class
            // Fungsi = menentukan format pada Date (misalnya format jam, menit, detik) sesuai keinginan user
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

            String inputJamMulaiKerja, inputJamSelesaiKerja, statusKerja = ""; int inputJumlahHariKerja;

            do
            {
                System.out.println("2 >> Input Jam Kerja");
                System.out.println("-------------------- \n");

                // Beri ketentuan format: Bila tidak sesuai dengan format jam tersebut, user tidak dapat lanjut
                System.out.println("====================================================");
                System.out.println("i << Attention - Formatting");
                System.out.println("Format jam = (hh:mm). Misalnya 09:55");
                System.out.println("====================================================");
                App.osSystem_Pause();

                System.out.print("Jumlah hari kerja         = "); inputJumlahHariKerja = InputValue.nextInt();  // Masukkan jumlah hari kerja dalam sebulan
                InputValue.nextLine();
                
                System.out.print("Jam mulai kerja (hh:mm)   = "); inputJamMulaiKerja = InputValue.nextLine();   // Masukkan jam berapa mulai kerja
                System.out.print("Jam selesai kerja (hh:mm) = "); inputJamSelesaiKerja = InputValue.nextLine(); // Masukkan jam berapa selesai kerja

                // Bila data hh:mm dari user TIDAK SESUAI dengan format, maka user tidak bisa lanjut
                if (inputJamMulaiKerja.charAt(2) != ':' || inputJamSelesaiKerja.charAt(2) != ':')
                {
                    System.out.println("Input Anda memiliki kesalahan format.");
                    System.out.println("Baca \"Attention\" sebelum menginput data waktu."); App.osSystem_Pause();
                }

                // bila data hh:mm dari user SESUAI dengan format, maka user bisa lanjut
                else if (inputJamMulaiKerja.charAt(2) == ':' && inputJamSelesaiKerja.charAt(2) == ':')
                {
                    break;
                }
            } while (true);

            Date jamMulaiKerja = sdf.parse(inputJamMulaiKerja);          // ubah data String -> Date
            Date jamSelesaiKerja = sdf.parse(inputJamSelesaiKerja);      // ubah data String -> Date

            App.osSystem_Pause();
            System.out.println("=============================================");
            System.out.println("1. Full Time  [Kerja tetap]                  ");
            System.out.println("2. Part Time  [Kerja tidak tetap]            ");
            System.out.println("3. Internship [Magang]                       ");
            System.out.println("4. Freelance  [Kerja sementara]              ");
            System.out.println("============================================= \n");

            System.out.print("Status Kerja (1 - 4) = "); int inputStatusKerja = InputValue.nextInt(); InputValue.nextLine();
            if (inputStatusKerja == 1) {
                statusKerja = "full time";
            }
            else if (inputStatusKerja == 2) {
                statusKerja = "part time";
            }
            else if (inputStatusKerja == 3) {
                statusKerja = "internship";
            }
            else if (inputStatusKerja == 4) {
                statusKerja = "freelance";
            }

            dataAkunAdministrator.setDataJadwalKerjaAdmin(new JadwalKerjaKaryawan(jamMulaiKerja, jamSelesaiKerja, statusKerja, inputJumlahHariKerja));
            App.osSystem_Pause();
        }
        catch (ParseException e)
        {
            System.out.println("Go ahead");
        }
    }

    static void tampilkanIdentitas()
    {
        AkunKaryawan.dataIdentitasKaryawan();
    }

    static void tampilkanJamKerja()
    {
        AkunKaryawan.dataJadwalKerjaKaryawan;
    }

    static void LogOut()
    {
        String[] argumentsList = {"1", "2", "3"};
        System.out.println("=======================");
        System.out.println("0 >> Log Out");

        System.out.print("Please select any key to continue... "); InputValue.nextLine();
        System.out.println(); App.main(argumentsList);
    }

    /**
     * @author Albert Cenderawan (03082190015)
     * Pull git on 11/07/2020 - 12.13
     */
}