package ManajemenKaryawan;
import java.util.Date;

/**
 * Waktu kerja yang disusun admin
 */

public class JadwalKerjaAdmin 
{
    private Date jamMulaiKerja;
    private Date jamSelesaiKerja;
    private String statusKerja;
    private Integer jumlahHariKerja;

    public JadwalKerjaAdmin(){}
    public JadwalKerjaAdmin(Date jamMulaiKerja, Date jamSelesaiKerja, String statusKerja, Integer jumlahHariKerja)
    {
        this.jamMulaiKerja = jamMulaiKerja;
        this.jamSelesaiKerja = jamSelesaiKerja;
        this.statusKerja = statusKerja;
        this.jumlahHariKerja = jumlahHariKerja;
    }

    public void setJamMulaiKerja(Date jamMulaiKerja) {
        this.jamMulaiKerja = jamMulaiKerja;
    }

    public void setJamSelesaiKerja(Date jamSelesaiKerja) {
        this.jamSelesaiKerja = jamSelesaiKerja;
    }

    public void setStatusKerja(String statusKerja) {
        this.statusKerja = statusKerja;
    }

    public void setJumlahHariKerja(Integer jumlahHariKerja) {
        this.jumlahHariKerja = jumlahHariKerja;
    }

    public Date getJamMulaiKerja() {
        return this.jamMulaiKerja;
    }

    public Date getJamSelesaiKerja() {
        return this.jamSelesaiKerja;
    }

    public String getStatusKerja() {
        return this.statusKerja;
    }

    public Integer JumlahHariKerja() {
        return this.jumlahHariKerja;
    }
}