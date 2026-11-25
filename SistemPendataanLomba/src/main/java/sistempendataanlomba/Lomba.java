package sistempendataanlomba;

/**
 * ============================================================================
 * PENERAPAN PRINSIP OOP:
 * 
 * 1. ENKAPSULASI (Encapsulation):
 *    - Atribut-atribut data perlombaan (idLomba, namaLomba, kuota) dideklarasikan 
 *      sebagai 'private' agar aman dan tidak dimodifikasi sembarangan.
 *    - Akses data disediakan melalui method getter dan setter publik.
 * ============================================================================
 * 
 * Class Lomba merepresentasikan cabang perlombaan yang diadakan.
 */
public class Lomba {

    // Atribut privat (Menerapkan Enkapsulasi)
    private String idLomba;
    private String namaLomba; // Contoh: Jalan Santai, Futsal, Lomba Mewarnai
    private int kuota;

    /**
     * Konstruktor Lomba.
     * 
     * @param idLomba Kode unik cabang lomba (contoh: L01, L02)
     * @param namaLomba Nama kegiatan/cabang perlombaan
     * @param kuota Jumlah kuota maksimal peserta/tim yang diterima
     */
    public Lomba(String idLomba, String namaLomba, int kuota) {
        this.idLomba = idLomba;
        this.namaLomba = namaLomba;
        this.kuota = kuota;
    }

    // ========================================================================
    // GETTER & SETTER (Menerapkan Enkapsulasi)
    // ========================================================================

    public String getIdLomba() {
        return idLomba;
    }

    public void setIdLomba(String idLomba) {
        this.idLomba = idLomba;
    }

    public String getNamaLomba() {
        return namaLomba;
    }

    public void setNamaLomba(String namaLomba) {
        this.namaLomba = namaLomba;
    }

    public int getKuota() {
        return kuota;
    }

    public void setKuota(int kuota) {
        this.kuota = kuota;
    }

    /**
     * Menampilkan data ringkas lomba.
     */
    public void displayInfo() {
        System.out.println("ID Lomba   : " + idLomba);
        System.out.println("Nama Lomba : " + namaLomba);
        System.out.println("Kuota Max  : " + kuota + " peserta/tim");
    }
}
