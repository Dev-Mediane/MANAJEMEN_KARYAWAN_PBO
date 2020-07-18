package ManajemenKaryawan;
import java.text.ParseException;
import java.util.Date;

/**
 * Lokasi proses perhitungan gaji/ penghasilan karyawan
 */

public class PenghasilanKaryawan
{
    AkunKaryawan dataAkunKaryawan;
    private Integer totalPenghasilan;
    private double lamaWaktuKerja;
    private int selisihJam, selisihMenit;

    int penghasilanPerJam()
    {
        int upahPerJam;  // jumlah upah/ gaji yang dihitung per jamnya

        if (dataAkunKaryawan.getDataJadwalKerjaKaryawan().getStatusKerja().equals("free time"))
        {
            // FULL TIME
            upahPerJam = 5000000 / 173;
            return upahPerJam;
        }
        else if (dataAkunKaryawan.getDataJadwalKerjaKaryawan().getStatusKerja().equals("part time"))
        {
            // PART TIME
            upahPerJam = 2000000 / 173;
            return upahPerJam;
        }
        else if (dataAkunKaryawan.getDataJadwalKerjaKaryawan().getStatusKerja().equals("internship"))
        {
            // INTERNSHIP
            upahPerJam = 3000000 / 173;
            return upahPerJam;
        }
        else if (dataAkunKaryawan.getDataJadwalKerjaKaryawan().getStatusKerja().equals("freelance"))
        {
            // FREELANCE
            upahPerJam = 4000000 / 173;
            return upahPerJam;
        }
        else {return 0;}
    }

    Integer calculate()
    {
        System.out.println("Status Kerja = " + dataAkunKaryawan.getDataJadwalKerjaKaryawan().getStatusKerja());
        Date jamMulaiKerja, jamSelesaiKerja;
        String statusKerja; Integer jumlahHariKerja;

        // Isi = data jadwal kerja yang diproses oleh karyawan
        jamMulaiKerja = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJamMulaiKerja();
        jamSelesaiKerja = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJamSelesaiKerja();
        statusKerja = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getStatusKerja();
        jumlahHariKerja = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJumlahHariKerja();

        // perbedaan jam mulai kerja dan jam selesai kerja
        double timeDifference01 = jamSelesaiKerja.getTime() - jamMulaiKerja.getTime();

        selisihJam = (int) Math.floor(timeDifference01 / 3600000); double timeDifference02 = timeDifference01 % 3600000;
        selisihMenit = (int) Math.floor(timeDifference02 / 60000);

        lamaWaktuKerja = selisihJam + (selisihMenit / 60);

        totalPenghasilan = (int) (lamaWaktuKerja * jumlahHariKerja * penghasilanPerJam());
        return this.totalPenghasilan;
    }

    void display()
    {
        // Isi = tampilkan Slip Gaji yang akan diterima pegawai/ karyawan
        System.out.println("Total diterima   = " + calculate());
    }
}