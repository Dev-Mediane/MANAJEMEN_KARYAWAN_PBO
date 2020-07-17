package ManajemenKaryawan;
import java.text.ParseException;
import java.util.Date;

/**
 * Lokasi proses perhitungan gaji/ penghasilan karyawan
 */

public class PenghasilanKaryawan
{
    private int totalPenghasilan;
    private Date jamMulaiKerja;
    private Date jamSelesaiKerja;
    private String statusKerja;
    private int jumlahHariKerja;
    private double lamaWaktuKerja;
    private int selisihJam, selisihMenit;

    int penghasilanPerJam(String statusKerja)
    {
        int upahPerJam;  // jumlah upah/ gaji yang dihitung per jamnya

        if (statusKerja.equals("full time"))
        {
            // FULL TIME
            upahPerJam = 5000000 / 173;
            return upahPerJam;
        }
        else if (statusKerja.equals("part time"))
        {
            // PART TIME
            upahPerJam = 2000000 / 173;
            return upahPerJam;
        }
        else if (statusKerja.equals("internship"))
        {
            // INTERNSHIP
            upahPerJam = 3000000 / 173;
            return upahPerJam;
        }
        else if (statusKerja.equals("freelance"))
        {
            // FREELANCE
            upahPerJam = 4000000 / 173;
            return upahPerJam;
        }
        else {return 0;}
    }

    int calculate(AkunKaryawan dataAkunKaryawan) throws ParseException
    {
        // Isi = data jadwal kerja yang diproses oleh karyawan
        this.jamMulaiKerja = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJamMulaiKerja();
        this.jamSelesaiKerja = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJamSelesaiKerja();
        this.statusKerja = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getStatusKerja();
        this.jumlahHariKerja = dataAkunKaryawan.getDataJadwalKerjaKaryawan().getJumlahHariKerja();

        // perbedaan jam mulai kerja dan jam selesai kerja
        double timeDifference01 = this.jamSelesaiKerja.getTime() - this.jamMulaiKerja.getTime();

        selisihJam = (int) Math.floor(timeDifference01 / 3600000); double timeDifference02 = timeDifference01 % 3600000;
        selisihMenit = (int) Math.floor(timeDifference02 / 60000);

        this.lamaWaktuKerja = selisihJam + (selisihMenit / 60);

        this.totalPenghasilan = (int) (this.lamaWaktuKerja * this.jumlahHariKerja * penghasilanPerJam(statusKerja));
        return this.totalPenghasilan;
    }

    void display(AkunKaryawan dataAkunKaryawan)
    {
        // Isi = tampilkan Slip Gaji yang akan diterima pegawai/ karyawan
        System.out.println("Total diterima   = " + calculate(dataAkunKaryawan));
    }
}