package sistempendataanlomba;

/**
 * ============================================================================
 * PENERAPAN PRINSIP OOP:
 * 
 * 1. ENKAPSULASI (Encapsulation):
 *    - Seluruh data transaksi pendaftaran (idPendaftaran, peserta, lomba, 
 *      tanggalDaftar) dibungkus secara privat dengan getter dan setter.
 * 
 * 2. POLIMORFISME (Polymorphic References & Dynamic Method Dispatch):
 *    - Atribut 'peserta' bertipe superclass 'Peserta'.
 *    - Atribut ini dapat menampung objek turunan apa pun, baik PesertaIndividu 
 *      maupun PesertaTim (Polymorphic Assignment).
 *    - Saat method 'peserta.displayInfo()' dipanggil di dalam 'displayTransaksi()',
 *      JVM secara otomatis memanggil versi subclass yang sesuai (Polimorfisme Dinamis).
 * ============================================================================
 * 
 * Class Pendaftaran bertindak sebagai entitas transaksi yang menghubungkan 
 * Peserta dengan Lomba yang diikutinya.
 */
public class Pendaftaran {

    // Atribut privat (Menerapkan Enkapsulasi)
    private String idPendaftaran;
    private Peserta peserta; // Polimorfisme: referensi superclass menampung subclass apapun
    private Lomba lomba;
    private String tanggalDaftar;

    /**
     * Konstruktor Pendaftaran.
     * 
     * @param idPendaftaran ID atau kode registrasi transaksi
     * @param peserta Objek Peserta (PesertaIndividu atau PesertaTim)
     * @param lomba Objek Lomba yang didaftarkan
     * @param tanggalDaftar Tanggal saat pendaftaran dilakukan
     */
    public Pendaftaran(String idPendaftaran, Peserta peserta, Lomba lomba, String tanggalDaftar) {
        this.idPendaftaran = idPendaftaran;
        this.peserta = peserta;
        this.lomba = lomba;
        this.tanggalDaftar = tanggalDaftar;
    }

    // ========================================================================
    // GETTER & SETTER (Menerapkan Enkapsulasi)
    // ========================================================================

    public String getIdPendaftaran() {
        return idPendaftaran;
    }

    public void setIdPendaftaran(String idPendaftaran) {
        this.idPendaftaran = idPendaftaran;
    }

    public Peserta getPeserta() {
        return peserta;
    }

    public void setPeserta(Peserta peserta) {
        this.peserta = peserta;
    }

    public Lomba getLomba() {
        return lomba;
    }

    public void setLomba(Lomba lomba) {
        this.lomba = lomba;
    }

    public String getTanggalDaftar() {
        return tanggalDaftar;
    }

    public void setTanggalDaftar(String tanggalDaftar) {
        this.tanggalDaftar = tanggalDaftar;
    }

    /**
     * Menampilkan rincian transaksi pendaftaran.
     * Mendemonstrasikan Polimorfisme pada pemanggilan peserta.displayInfo().
     */
    public void displayTransaksi() {
        System.out.println("ID Registrasi  : " + idPendaftaran);
        System.out.println("Tanggal Daftar : " + tanggalDaftar);
        System.out.println("Nama Lomba     : " + lomba.getNamaLomba() + " (Kode: " + lomba.getIdLomba() + ")");
        System.out.println("--- Data Peserta ---");
        // Pemanggilan method polimorfis
        peserta.displayInfo();
        System.out.println("----------------------------------------");
    }
}
