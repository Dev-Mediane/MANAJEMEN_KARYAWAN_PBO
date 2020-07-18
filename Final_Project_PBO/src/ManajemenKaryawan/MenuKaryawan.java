package ManajemenKaryawan;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import ManajemenKaryawan.AkunKaryawan;
import ManajemenKaryawan.App;
import java.util.Date;

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
        System.out.println("Welcome to Employee Menu, " + String.copyValueOf(dataAkunKaryawan.getDataRegistrasi().getNomorID()) + "!");
        System.out.println("------------------------------\n");

        System.out.println("==============================  ");
        System.out.println("1 >> Input Identitas            ");
        System.out.println("2 >> Input Jam Kerja            ");
        System.out.println("3 >> Lihat Gaji                 ");
        System.out.println("4 >> Ubah Identitas             ");
        System.out.println("5 >> Ubah Jam Kerja             ");
        System.out.println("6 >> Tampilkan Identitas        ");
        System.out.println("7 >> Tampilkan Jam Kerja        ");
        System.out.println("0 >> Log Out                    ");
        System.out.println("==============================\n");

        System.out.print("Pilihan Menu: "); String option = InputValue.nextLine();
        navigateOptions(option);
    }

    static void navigateOptions(String pilihan)
    {
        if (pilihan.equals("input identitas") || pilihan.equals("1")) {
            inputIdentitas(); interfaceMenu();
        }
        else if (pilihan.equals("input jam kerja") || pilihan.equals("2")) {
            inputJamKerja(); interfaceMenu();
        }
        else if (pilihan.equals("lihat gaji") || pilihan.equals("3")) {
            lihatGaji(); interfaceMenu();
        }
        else if (pilihan.equals("ubah identitas") || pilihan.equals("4")) {
            ubahIdentitas(); interfaceMenu();
        }
        else if (pilihan.equals("ubah jam kerja") || pilihan.equals("5")) {
            ubahJamKerja(); interfaceMenu();
        }
        else if (pilihan.equals("tampilkan identitas") || pilihan.equals("6")) {
            tampilkanIdentitas(); interfaceMenu();
        }
        else if (pilihan.equals("tampilkan jam kerja") || pilihan.equals("7")) {
            tampilkanJamKerja(); interfaceMenu();
        }
        else if (pilihan.equals("log out") || pilihan.equals("0")) {
            LogOut();
        }
    }

    static void inputIdentitas()
    {
        // Di menu ini, user (karyawan) menginput identitasnya di sini
        String inputNamaLengkap, inputNomorHP, inputEmail; int inputKendaraan;
        System.out.println("1 >> Input Identitas");
        System.out.println("-------------------- \n");

        System.out.print("Nama Lengkap      = "); inputNamaLengkap = InputValue.nextLine();
        System.out.print("Nomor HP          = "); inputNomorHP = InputValue.nextLine();
        System.out.print("E-mail            = "); inputEmail = InputValue.nextLine(); 
        System.out.print("Jumlah Kendaraan  = "); inputKendaraan = InputValue.nextInt(); InputValue.nextLine();
        System.out.println();

        // Selanjutnya, data input tersebut akan ditempatkan di Database
        dataAkunKaryawan.setDataIdentitasKaryawan(new IdentitasKaryawan(inputNamaLengkap, inputNomorHP.toCharArray(), inputEmail, inputKendaraan));
        App.osSystem_Pause();
    }

    static void inputJamKerja()
    {
        // Hal yang sama juga terjadi dalam menu "Input Jam Kerja"
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("kk:mm");
            String inputJamMulaiKerja, inputJamSelesaiKerja, statusKerja = ""; 
            Integer inputJumlahHariKerja = null;

            do
            {
                System.out.println("2 >> Input Jam Kerja");
                System.out.println("-------------------- \n");

                // Di menu ini, user dapat menyusun jam kerja sendiri. Akan tetapi..
                // User harus mengisi data waktu dengan sangat teliti (mengikuti format di bawah)
                // Di bawah, dicantumkan bahwa format jam yang benar ialah hh:mm (jam:waktu)

                System.out.println("====================================================");
                System.out.println("i << Attention - Formatting");
                System.out.println("Format jam = (hh:mm). Misalnya 09:55");
                System.out.println("==================================================== \n");
                App.osSystem_Pause();

                System.out.print("Jumlah hari kerja         = "); inputJumlahHariKerja = InputValue.nextInt();  // Masukkan jumlah hari kerja dalam sebulan
                InputValue.nextLine();
                
                System.out.print("Jam mulai kerja (hh:mm)   = "); inputJamMulaiKerja = InputValue.nextLine();   // Masukkan jam berapa mulai kerja
                System.out.print("Jam selesai kerja (hh:mm) = "); inputJamSelesaiKerja = InputValue.nextLine(); // Masukkan jam berapa selesai kerja
                System.out.println();

                // Bila data berformat "hh:mm" dari user TIDAK SESUAI dengan format, maka user tidak bisa lanjut
                if (inputJamMulaiKerja.charAt(2) != ':' || inputJamSelesaiKerja.charAt(2) != ':')
                {
                    System.out.println("Input Anda memiliki kesalahan format.");
                    System.out.println("Baca \"Attention\" sebelum menginput data waktu.");
                    System.out.println("------------------------------------------------ \n"); 
                    App.osSystem_Pause();
                }

                // bila data berformat "hh:mm" dari user SESUAI dengan format, maka user bisa lanjut
                else if (inputJamMulaiKerja.charAt(2) == ':' && inputJamSelesaiKerja.charAt(2) == ':')
                {
                    App.osSystem_Pause(); System.out.println();
                    break;
                }
            } while (true);

            // Data waktu (berformat hh:mm) yang diinput user akan dikonversi ke tipe data Date
            Date jamMulaiKerja = sdf.parse(inputJamMulaiKerja);          // ubah data String -> Date
            Date jamSelesaiKerja = sdf.parse(inputJamSelesaiKerja);      // ubah data String -> Date

            // Selanjutnya, user akan menentukan apa status kerjanya..
            // apakah ia jadi Full Time Employee, Part Time Employee, dst.
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

            // Setelah data terinput, maka data input tersebut akan ditransfer ke Database perusahaan
            dataAkunKaryawan.setDataJadwalKerjaKaryawan(new JadwalKerjaKaryawan(jamMulaiKerja, jamSelesaiKerja, statusKerja, inputJumlahHariKerja));
            App.osSystem_Pause(); System.out.println();
        }
        catch (Exception e)
        {
            System.out.println("Go ahead");
        }
    }

    static void lihatGaji()
    {
        // Di menu ini, user akan mengamati berapa banyak gaji yang didapatnya
        // Gaji karyawan ditentukan oleh Jam Kerja Karyawan itu sendiri

        System.out.println("3 >> Lihat Gaji"); 
        System.out.println("--------------- \n");

        Date jamMulaiKerjaTerkait = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJamMulaiKerja();
        Date jamSelesaiKerjaTerkait = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJamSelesaiKerja();
        Integer jumlahHariKerjaTerkait = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJumlahHariKerja();
        String statusKerjaTerkait = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getStatusKerja();

        // Akan tetapi, sebelum menampilkan gaji, program akan mengecek apakah jadwal kerja sudah diisi karyawan atau belum
        // Apabila belum, karyawan terkait tidak dapat mengamati gajinya
        if (jamMulaiKerjaTerkait == null || jamSelesaiKerjaTerkait == null || jumlahHariKerjaTerkait == null || statusKerjaTerkait == null)
        {
            System.out.println("Maaf, Anda belum menginput jam kerja.");
            System.out.println("------------------------------------- \n");
            App.osSystem_Pause(); interfaceMenu();
        }

        // Bagaimana kalau data Jam Kerja sudah diisi oleh user sebelumnya?
        // Sebuah file bernama PenghasilanKaryawan.java akan memproses data Jam Kerja karyawan, 
        // lalu mengirimkan total gaji karyawan di sini
        else if (jamMulaiKerjaTerkait != null && jamSelesaiKerjaTerkait != null && jumlahHariKerjaTerkait != null && statusKerjaTerkait != null)
        {
            PenghasilanKaryawan dataPenghasilanKaryawan = new PenghasilanKaryawan();
            dataPenghasilanKaryawan.dataAkunKaryawan = dataAkunKaryawan;

            dataAkunKaryawan.getdataPenghasilanKaryawan().display();
            App.osSystem_Pause(); interfaceMenu();
        }
    }

    static void ubahIdentitas()
    {
        // Dalam menu ini, user dapat mengubah identitasnya apabila diperlukan
        boolean perubahanIdentitas = false;
        do
        {
            System.out.println("4 >> Ubah Identitas");
            System.out.println("------------------- \n");
            
            System.out.println("===================");
            System.out.println("1. Nama Lengkap    ");
            System.out.println("2. Nomor HP        ");
            System.out.println("3. Alamat E-mail   ");
            System.out.println("4. Jumlah Kendaraan");
            System.out.println("=================== \n");

            System.out.print("Bagian yang ingin diubah = "); int pilihan = InputValue.nextInt(); InputValue.nextLine();
            System.out.println();
            
            switch (pilihan)
            {
                // User dapat memilih, apakah mau mengubah nama lengkapnya saja, atau
                case 1:
                    System.out.println("Nama Lengkap (old) = " + dataAkunKaryawan.getDataIdentitasKaryawan().getnamaLengkap());
                    System.out.print("Nama Lengkap (new) = "); String inputNamaLengkapBaru = InputValue.nextLine();

                    dataAkunKaryawan.getDataIdentitasKaryawan().setnamaLengkap(inputNamaLengkapBaru);
                    perubahanIdentitas = true; break;

                // mengubah nomor HP-nya saja, atau
                case 2:
                    System.out.println("Nomor HP (lama) = " + String.copyValueOf(dataAkunKaryawan.getDataIdentitasKaryawan().getNomorHP()));
                    System.out.print("Nomor HP (baru) = "); String inputNomorHPBaru = InputValue.nextLine();

                    dataAkunKaryawan.getDataIdentitasKaryawan().setnomorHP(inputNomorHPBaru.toCharArray());
                    perubahanIdentitas = true; break;

                // mengubah alamat e-mail miliknya, ataupun
                case 3:
                    System.out.println("Alamat e-mail (old) = " + dataAkunKaryawan.getDataIdentitasKaryawan().getemail());
                    System.out.print("Alamat e-mail (new) = "); String inputEmailBaru = InputValue.nextLine();

                    dataAkunKaryawan.getDataIdentitasKaryawan().setemail(inputEmailBaru);
                    perubahanIdentitas = true; break;

                // mengubah jumlah kendaraan yang dimilikinya.
                case 4:
                    System.out.println("Jumlah Kendaraan (old) = " + dataAkunKaryawan.getDataIdentitasKaryawan().getkendaraan());
                    System.out.print("Jumlah Kendaraan (new) = "); Integer inputJumlahKendaraanBaru = InputValue.nextInt();

                    dataAkunKaryawan.getDataIdentitasKaryawan().setkendaraan(inputJumlahKendaraanBaru);
                    perubahanIdentitas = true; break;

                // Kalau user memberikan pilihan yang salah, maka akan muncul pesan error
                default:
                    System.out.println("--------------------------------------");
                    System.out.println("Pilihan yang Anda input tidak tertera.");
                    System.out.println("Pilihan hanya dari 1 - 3");
                    System.out.println("-------------------------------------- \n");
            }
        } while (perubahanIdentitas == false);
    }

    static void ubahJamKerja()
    {
        // Menu ini mirip seperti ubahIdentitas(), akan tetapi user di sini justru mengubah jam kerjanya
        // Dengan mengubah jam kerjanya, otomatis nilai gaji yang diterima juga akan berubah
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("kk:mm");
            String inputJamMulaiKerja, inputJamSelesaiKerja; 
            Integer inputJumlahHariKerja = null;
            
            System.out.println("5 >> Ubah Jam Kerja");
            System.out.println("-------------------- \n");

            System.out.println("====================================================");
            System.out.println("i << Attention - Formatting");
            System.out.println("Format jam = (hh:mm). Misalnya 09:55");
            System.out.println("====================================================");
            System.out.println();

            System.out.println("------------------------------");
            System.out.println("1. Jam Masuk Kerja");
            System.out.println("2. Jam Selesai Kerja");
            System.out.println("3. Jumlah Hari Kerja per Bulan");
            System.out.println("4. Status Kerja");
            System.out.println("------------------------------ \n");

            System.out.print("Kategori data yang diubah = "); int pilihan = InputValue.nextInt();
            switch (pilihan)
            {
                // User dapat mengubah jam berapa ia masuk kerja
                case 1:
                    System.out.println("Input lama (HH:MM) = " + sdf.format(dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJamMulaiKerja()));
                    System.out.print("Input baru (HH:MM) = "); inputJamMulaiKerja = InputValue.nextLine();

                    Date jamMulaiKerja = sdf.parse(inputJamMulaiKerja);
                    dataAkunKaryawan.getDataJadwalKerjaKaryawan().setJamMulaiKerja(jamMulaiKerja);
                    break;

                // User dapat mengubah jam berapa ia selesai kerja
                case 2:
                    System.out.println("Input lama (HH:MM) = " + sdf.format(dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJamSelesaiKerja()));
                    System.out.print("Input baru (HH:MM) = "); inputJamSelesaiKerja = InputValue.nextLine();

                    Date jamSelesaiKerja = sdf.parse(inputJamSelesaiKerja);
                    dataAkunKaryawan.getDataJadwalKerjaKaryawan().setJamSelesaiKerja(jamSelesaiKerja);
                    break;

                // User dapat mengubah berapa hari ia bekerja dalam 1 bulan
                case 3:
                    System.out.println("Input lama = " + dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJumlahHariKerja());
                    System.out.print("Input baru = "); inputJumlahHariKerja = InputValue.nextInt(); InputValue.nextLine();

                    dataAkunKaryawan.getDataJadwalKerjaKaryawan().setJumlahHariKerja(inputJumlahHariKerja);
                    break;

                // User dapat mengubah status kerjanya
                case 4:
                    System.out.println("=============================================");
                    System.out.println("1. Full Time  [Kerja tetap]                  ");
                    System.out.println("2. Part Time  [Kerja tidak tetap]            ");
                    System.out.println("3. Internship [Magang]                       ");
                    System.out.println("4. Freelance  [Kerja sementara]              ");
                    System.out.println("=============================================");
                    System.out.print("Status Kerja yang Baru = "); int inputStatusKerja = InputValue.nextInt();

                    switch (inputStatusKerja)
                    {
                        case 1:
                            dataAkunKaryawan.getDataJadwalKerjaKaryawan().setStatusKerja("full time");
                            break;
                        case 2:
                            dataAkunKaryawan.getDataJadwalKerjaKaryawan().setStatusKerja("part time");
                            break;
                        case 3:
                            dataAkunKaryawan.getDataJadwalKerjaKaryawan().setStatusKerja("internship");
                            break;
                        case 4:
                            dataAkunKaryawan.getDataJadwalKerjaKaryawan().setStatusKerja("freelance");
                            break;
                        default:
                            System.out.println("Maaf, Anda salah input.");
                    }
                    break;
                default:
                    System.out.println("Maaf, pilihan Anda tidak sesuai.");
            }
        }
        catch (ParseException e)
        {
            System.out.println("Go ahead.");
        }
    }

    static void tampilkanIdentitas()
    {
        // Dalam menu ini, identitas karyawan dalam menu ini akan ditampilkan
        System.out.println("6 >> Tampilkan Identitas");
        System.out.println("------------------------ \n");

        System.out.println("Nama Lengkap     = " + dataAkunKaryawan.getDataIdentitasKaryawan().getnamaLengkap());        
        System.out.println("Nomor HP         = " + String.copyValueOf(dataAkunKaryawan.getDataIdentitasKaryawan().getNomorHP())); System.out.println();

        System.out.println("Alamat e-mail    = " + dataAkunKaryawan.getDataIdentitasKaryawan().getemail());
        System.out.println("Jumlah kendaraan = " + dataAkunKaryawan.getDataIdentitasKaryawan().getkendaraan()); System.out.println();
        
        App.osSystem_Pause();
    }

    static void tampilkanJamKerja()
    {
        // Dalam menu ini, identitas administrator dalam menu ini akan ditampilkan
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm");
        System.out.println(" >> Tampilkan Jam Kerja");
        System.out.println("----------------------- \n");

        System.out.println("Nama Lengkap    = " + dataAkunKaryawan.getDataIdentitasKaryawan().getnamaLengkap());
        System.out.println("Status Kerja    = " + dataAkunKaryawan.getDataJadwalKerjaKaryawan().getStatusKerja()); System.out.println();

        System.out.println("Jam Mulai Kerja             = " + sdf.format(dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJamMulaiKerja()));
        System.out.println("Jam Selesai Kerja           = " + sdf.format(dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJamSelesaiKerja()));
        System.out.println("Jumlah Hari Kerja per Bulan = " + dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJumlahHariKerja());

        App.osSystem_Pause();
    }

    static void LogOut()
    {
        // Menu ini berguna bagi user untuk Log Out (keluar dari Menu Karyawan)
        String[] argumentsList = {"1", "2", "3"};
        System.out.println("=======================");
        System.out.println("0 >> Log Out");

        System.out.print("Please select any key to continue... "); InputValue.nextLine();
        System.out.println(); App.main(argumentsList);
    }
}