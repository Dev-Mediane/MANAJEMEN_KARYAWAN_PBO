package ManajemenKaryawan;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Lokasi proses perhitungan gaji/ penghasilan karyawan
 */

public class PenghasilanKaryawan
{
    private AkunKaryawan dataAkunKaryawan;
    private Integer totalPenghasilan;
    private double lamaWaktuKerja;
    private int selisihJam, selisihMenit;

    public PenghasilanKaryawan(){}
    public PenghasilanKaryawan(AkunKaryawan dataAkunKaryawan)
    {
        this.dataAkunKaryawan = dataAkunKaryawan;
    }

    int penghasilanPerJam()
    {
        // Dalam method ini, program akan menghitung berapa penghasilan user per jam (berdasarkan status kerja)
        int upahPerJam;

        // Setiap status kerja user memiliki penghasilan per jam yang berbeda - beda
        // Contoh: Gaji karyawan full time berbeda dari part time, sekalipun lama waktu kerja sama
        if (dataAkunKaryawan.getDataJadwalKerjaKaryawan().getStatusKerja().equals("full time"))
        {
            upahPerJam = 5000000 / 173;
            return upahPerJam;
        }
        else if (dataAkunKaryawan.getDataJadwalKerjaKaryawan().getStatusKerja().equals("part time"))
        {
            upahPerJam = 2000000 / 173;
            return upahPerJam;
        }
        else if (dataAkunKaryawan.getDataJadwalKerjaKaryawan().getStatusKerja().equals("internship"))
        {
            upahPerJam = 3000000 / 173;
            return upahPerJam;
        }
        else if (dataAkunKaryawan.getDataJadwalKerjaKaryawan().getStatusKerja().equals("freelance"))
        {
            upahPerJam = 4000000 / 173;
            return upahPerJam;
        }
        else {return 0;}
    }

    Integer calculate()
    {
        // Tujuan = menghitung gaji karyawan berdasarkan data jadwal kerja yang diinput
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        Date jamMulaiKerja, jamSelesaiKerja;
        String statusKerja; Integer jumlahHariKerja;

        // Data jadwal kerja yang diinput karyawan akan ditempatkan di sini
        jamMulaiKerja = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJamMulaiKerja();
        jamSelesaiKerja = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJamSelesaiKerja();
        statusKerja = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getStatusKerja();
        jumlahHariKerja = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJumlahHariKerja();

        // Mula - mula, program akan menghitung lama waktu kerja user per bulan
        // Lalu, program memperoleh selisih waktu dalam bentuk milidetik (sesuai konvensi Java)
        double timeDifference01 = jamSelesaiKerja.getTime() - jamMulaiKerja.getTime();
        
        // Waktu tersebut lalu dikonversikan dari "milidetik" ke "jam"
        selisihJam = (int) Math.floor(timeDifference01 / 3600000); double timeDifference02 = timeDifference01 % 3600000;

        // Sisanya, akan dikonversikan dari "milidetik" ke "menit"
        selisihMenit = (int) Math.floor(timeDifference02 / 60000);

        // Kemudian, lama waktu kerja akan dihitung
        lamaWaktuKerja = selisihJam + (selisihMenit / 60);

        // Di sisi lain, penghasilan per jam juga dihitung (berdasarkan status kerja)
        // Pada akhirnya, data - data tersebut akan dikalkulasikan bersama
        totalPenghasilan = (int) (lamaWaktuKerja * jumlahHariKerja * penghasilanPerJam());

        // sehingga akan muncul "total penghasilan" yang menjadi laba bersih user
        return this.totalPenghasilan;
    }

    void display()
    {
        // Method ini berfungsi untuk menampilkan gaji/ penghasilan karyawan
        String mataUang = "Rp";
        
        // Method ini nantinya dipakai oleh karyawan di MenuKaryawan
        System.out.println("=============================================   ");
        System.out.println("Status Kerja   = " + this.dataAkunKaryawan.getDataJadwalKerjaKaryawan().getStatusKerja().toUpperCase());
        System.out.println("Total diterima = " + mataUang + calculate());
        System.out.println("============================================= \n");
    }
}