package ManajemenKaryawan;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Date;
import java.txt.*;

/**
 * User interface dalam setiap "Log In" admin
 */

public class MenuAdministrator
{
    static AkunAdministrator dataAkunAdministrator;
    static Scanner InputValue = new Scanner(System.in);

    static void interfaceMenu()
    {
        System.out.println("---------------------------------------------------");
        System.out.println("Welcome, " + String.copyValueOf(dataAkunAdministrator.getDataRegistrasi().getNomorID()) + "!");
        System.out.println("---------------------------------------------------");

        System.out.println("===========================================  ");
        System.out.println("1  >> Input Identitas                        ");
        System.out.println("2  >> Input Jam Kerja                        ");
        System.out.println("3  >> Tampilkan Gaji Karyawan                ");
        System.out.println("4  >> Ubah Identitas                         ");
        System.out.println("5  >> Ubah Jam Kerja                         ");
        System.out.println("6  >> Tampilkan Identitas                    ");
        System.out.println("7  >> Tampilkan Jam Kerja                  \n");

        System.out.println("8  >> Tampilkan Data Karyawan                ");
        System.out.println("9  >> Tampilkan Data Administrator         \n");

        System.out.println("10 >> Cari Karyawan                          ");
        System.out.println("11 >> Cari Admin                             ");
        System.out.println("12 >> Cek Eksistensi Akun                  \n");
        
        System.out.println("0  >> Log Out                                ");
        System.out.println("===========================================\n");

        System.out.print("Pilihan Menu: "); String option = InputValue.nextLine();
        System.out.println(); navigateOptions(option);
    }

    static void navigateOptions(String option)
    {
        if (option.equals("input identitas") || option.equals("1"))
        {
            // input identitas admin
            inputIdentitas();
            interfaceMenu();
        }
        else if (option.equals("input jam kerja") || option.equals("2"))
        {
            // input jam kerja admin
            inputJamKerja();
            interfaceMenu();
        }
        else if (option.equals("tampilkan gaji karyawan") || option.equals("3"))
        {
            // hitung gaji karyawan
            tampilkanGajiKaryawan();
            interfaceMenu();
        }
        else if (option.equals("ubah identitas") || option.equals("4"))
        {
            // ubah identitas admin
            ubahIdentitas();
            interfaceMenu();
        }
        else if (option.equals("ubah jam kerja") || option.equals("5"))
        {
            // ubah jam kerja admin
            ubahJamKerja();
            interfaceMenu();
        }
        else if (option.equals("tampilkan identitas") || option.equals("6"))
        {
            // tampilkan identitas admin
            tampilkanIdentitas();
            interfaceMenu();
        }
        else if (option.equals("tampilkan jam kerja") || option.equals("7"))
        {
            // tampilkan jam kerja admin
            tampilkanJamKerja();
            interfaceMenu();
        }
        else if (option.equals("tampilkan data karyawan") || option.equals("8"))
        {
            tampilkanDataKaryawan();
            interfaceMenu();
        }
        else if (option.equals("tampilkan data administrator") || option.equals("9"))
        {
            tampilkanDataAdministrator();
            interfaceMenu();
        }
        else if (option.equals("cari karyawan") || option.equals("10"))
        {
            cariKaryawan();
            interfaceMenu();
        }
        else if (option.equals("cari admin") || option.equals("11"))
        {
            cariAdmin();
            interfaceMenu();
        }
        else if (option.equals("cek eksistensi akun") || option.equals("12"))
        {
            cekEksistensiAkun();
            interfaceMenu();
        }
        else if (option.equals("log out") || option.equals("0"))
        {
            // keluar dari akun admin
            LogOut();
        }
    }

    static void inputIdentitas()
    {
        System.out.println("1 >> Input Identitas");
        System.out.println("-------------------- \n");

        System.out.print("Nama Lengkap  = "); String inputNamaLengkap = InputValue.nextLine();
        System.out.print("Nomor HP      = "); String inputNomorHP = InputValue.nextLine();
        System.out.print("E-mail        = "); String inputEmail = InputValue.nextLine(); System.out.println();

        dataAkunAdministrator.setDataIdentitasAdmin(new IdentitasAdmin(inputNamaLengkap, inputNomorHP, inputEmail));
        App.osSystem_Pause();
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

            dataAkunAdministrator.setDataJadwalKerjaAdmin(new JadwalKerjaAdmin(jamMulaiKerja, jamSelesaiKerja, statusKerja, inputJumlahHariKerja));
            App.osSystem_Pause();
        }
        catch (ParseException e)
        {
            System.out.println("Go ahead");
        }
    }

    static void tampilkanGajiKaryawan()
    {
        String mataUang = "Rp";
        System.out.println("3 >> Tampilkan Gaji Karyawan");
        System.out.println("---------------------------- \n");

        System.out.println("Ada " + Database.accessDaftarKaryawan().size() + " akun karyawan yang masuk.");
        System.out.print("Konfirmasi = "); String konfirmasi = InputValue.nextLine();

        if (konfirmasi.equals("ya") || konfirmasi.equals("yes") || konfirmasi.equals("iya"))
        {
            for (int urutanDaftar = 0; urutanDaftar < Database.accessDaftarKaryawan().size(); urutanDaftar++)
            {
                System.out.println("EMPLOYEE " + (urutanDaftar + 1) + ":");
                System.out.println("---------------------------------------------");
                System.out.println("Nomor ID        = " + Database.accessDaftarKaryawan().get(urutanDaftar).getDataRegistrasi().getNomorID().toString());
                System.out.println("Nama Lengkap    = " + Database.accessDaftarKaryawan().get(urutanDaftar).getDataIdentitasKaryawan().getnamaLengkap());
                System.out.println("Status Kerja    = " + Database.accessDaftarKaryawan().get(urutanDaftar).getDataJadwalKerjaKaryawan().getStatusKerja()); System.out.println();

                System.out.println("Total Gaji Bersih = " + mataUang + Database.accessDaftarKaryawan().get(urutanDaftar).getdataPenghasilanKaryawan().calculate(Database.accessDaftarKaryawan().get(urutanDaftar)));
                System.out.println("--------------------------------------------- \n");
            }
        }
        App.osSystem_Pause();
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
                    System.out.println("Nama Lengkap (old) = " + dataAkunAdministrator.getDataIdentitasAdmin().getNamaLengkap());
                    System.out.print("Nama Lengkap (new) = "); String inputNamaLengkapBaru = InputValue.nextLine();

                    dataAkunAdministrator.getDataIdentitasAdmin().setNamaLengkap(inputNamaLengkapBaru);
                    perubahanIdentitas = true; break;

                case 2: // Hanya ubah nomor HP saja
                    System.out.println("Nomor HP (lama) = " + dataAkunAdministrator.getDataIdentitasAdmin().getnomorhp());
                    System.out.print("Nomor HP (baru) = "); String inputNomorHPBaru = InputValue.nextLine();

                    dataAkunAdministrator.getDataIdentitasAdmin().setNomorHP(inputNomorHPBaru);
                    perubahanIdentitas = true; break;

                case 3: // Hanya ubah alamat e-mail saja
                    System.out.println("Alamat e-mail (old) = " + dataAkunAdministrator.getDataIdentitasAdmin().getEmail());
                    System.out.print("Alamat e-mail (new) = "); String inputEmailBaru = InputValue.nextLine();

                    dataAkunAdministrator.getDataIdentitasAdmin().setEmail(inputEmailBaru);
                    perubahanIdentitas = true; break;

                default:
                    System.out.println("Pilihan yang Anda input tidak tertera.");
                    System.out.println("Pilihan hanya dari 1 - 3");
            }
        } while (perubahanIdentitas == false);
    }

    static void ubahJamKerja()
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            String inputJamMulaiKerja, inputJamSelesaiKerja; int inputJumlahHariKerja;
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
                case 1:
                    System.out.println("Input lama (HH:MM) = " + sdf.format(dataAkunAdministrator.getDataJadwalKerjaAdmin().getJamMulaiKerja()));
                    System.out.print("Input baru (HH:MM) = "); inputJamMulaiKerja = InputValue.nextLine();

                    Date jamMulaiKerja = sdf.parse(inputJamMulaiKerja);
                    dataAkunAdministrator.getDataJadwalKerjaAdmin().setJamMulaiKerja(jamMulaiKerja);
                    break;
                case 2:
                    System.out.println("Input lama (HH:MM) = " + sdf.format(dataAkunAdministrator.getDataJadwalKerjaAdmin().getJamSelesaiKerja()));
                    System.out.print("Input baru (HH:MM) = "); inputJamSelesaiKerja = InputValue.nextLine();

                    Date jamSelesaiKerja = sdf.parse(inputJamSelesaiKerja);
                    dataAkunAdministrator.getDataJadwalKerjaAdmin().setJamSelesaiKerja(jamSelesaiKerja);
                    break;
                case 3:
                    System.out.println("Input lama = " + dataAkunAdministrator.getDataJadwalKerjaAdmin().getJumlahHariKerja());
                    System.out.print("Input baru = "); inputJumlahHariKerja = InputValue.nextInt(); InputValue.nextLine();

                    dataAkunAdministrator.getDataJadwalKerjaAdmin().setJumlahHariKerja(inputJumlahHariKerja);
                    break;
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
                            dataAkunAdministrator.getDataJadwalKerjaAdmin().setStatusKerja("full time");
                            break;
                        case 2:
                            dataAkunAdministrator.getDataJadwalKerjaAdmin().setStatusKerja("part time");
                            break;
                        case 3:
                            dataAkunAdministrator.getDataJadwalKerjaAdmin().setStatusKerja("internship");
                            break;
                        case 4:
                            dataAkunAdministrator.getDataJadwalKerjaAdmin().setStatusKerja("freelance");
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
        System.out.println("6 >> Tampilkan Identitas");
        System.out.println("------------------------ \n");

        System.out.println("ID Administrator = " + String.copyValueOf(dataAkunAdministrator.getDataRegistrasi().getNomorID()));
        System.out.println("Nama Lengkap     = " + dataAkunAdministrator.getDataIdentitasAdmin().getNamaLengkap());
        System.out.println("Nomor HP         = " + dataAkunAdministrator.getDataIdentitasAdmin().getnomorhp());
        System.out.println("Alamat e-mail    = " + dataAkunAdministrator.getDataIdentitasAdmin().getEmail());
        System.out.println();
        
        App.osSystem_Pause();
    }

    static void tampilkanJamKerja()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        System.out.println("7 >> Tampilkan Jam Kerja");
        System.out.println("------------------------ \n");

        System.out.println("=============================================================");
        System.out.println("ID Administrator = " + String.copyValueOf(dataAkunAdministrator.getDataRegistrasi().getNomorID()));
        System.out.println("Status Kerja     = " + dataAkunAdministrator.getDataJadwalKerjaAdmin().getStatusKerja()); System.out.println();

        System.out.println("Jam Mulai Kerja               = " + sdf.format(dataAkunAdministrator.getDataJadwalKerjaAdmin().getJamMulaiKerja()));
        System.out.println("Jam Selesai Kerja             = " + sdf.format(dataAkunAdministrator.getDataJadwalKerjaAdmin().getJamSelesaiKerja()));
        System.out.println("Jumlah Hari Kerja (per bulan) = " + dataAkunAdministrator.getDataJadwalKerjaAdmin().getJumlahHariKerja());
        System.out.println("============================================================= \n");

        App.osSystem_Pause();
    }

    static void tampilkanDataKaryawan()
    {
        System.out.println("8 >> Tampilkan Data Karyawan");
        System.out.println("---------------------------- \n");

        for (int urutanDaftar = 0; urutanDaftar < Database.accessDaftarKaryawan().size(); urutanDaftar++)
        {
            System.out.println("==========================================================");
            System.out.println("Karyawan No. " + (urutanDaftar + 1) + ": ");
            System.out.println("Nomor ID        = " + String.copyValueOf(Database.accessDaftarAdministrator().get(urutanDaftar).getDataRegistrasi().getNomorID()));
            System.out.println("Nama Lengkap    = " + Database.accessDaftarKaryawan().get(urutanDaftar).getDataIdentitasKaryawan().getnamaLengkap());
            System.out.println("Status Kerja    = " + Database.accessDaftarKaryawan().get(urutanDaftar).getDataJadwalKerjaKaryawan().getStatusKerja());
            System.out.println("==========================================================");
        }
    }

    static void tampilkanDataAdministrator()
    {
        System.out.println("9 >> Tampilkan Data Administrator");
        System.out.println("--------------------------------- \n");

        for (int urutanDaftar = 0; urutanDaftar < Database.accessDaftarAdministrator().size(); urutanDaftar++)
        {
            System.out.println("==========================================================");
            System.out.println("Administrator No. " + (urutanDaftar + 1) + ": ");
            System.out.println("Nomor ID        = " + String.copyValueOf(Database.accessDaftarAdministrator().get(urutanDaftar).getDataRegistrasi().getNomorID()));
            System.out.println("Nama Lengkap    = " + Database.accessDaftarAdministrator().get(urutanDaftar).getDataIdentitasAdmin().getNamaLengkap());
            System.out.println("Status Kerja    = " + Database.accessDaftarAdministrator().get(urutanDaftar).getDataJadwalKerjaAdmin().getStatusKerja());
            System.out.println("==========================================================");
        }
    }

    static void 

    static void LogOut()
    {
        String[] argumentsList = {"1", "2", "3"};
        System.out.println("0 >> Log Out");
        System.out.println("------------ \n");

        System.out.print("Please select any key to continue... "); InputValue.nextLine();
        System.out.println(); App.main(argumentsList);
    }
}