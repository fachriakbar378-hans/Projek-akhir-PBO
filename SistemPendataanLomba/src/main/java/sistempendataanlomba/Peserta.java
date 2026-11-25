package sistempendataanlomba;

/**
 * ============================================================================
 * PENERAPAN PRINSIP OOP:
 * 
 * 1. ENKAPSULASI (Encapsulation):
 *    - Semua atribut dideklarasikan dengan access modifier 'private' untuk 
 *      menyembunyikan data internal objek dari akses langsung luar class.
 *    - Akses baca dan modifikasi nilai data dilakukan melalui method 'getter' 
 *      dan 'setter' (public).
 * 
 * 2. ABSTRAKSI (Abstraction):
 *    - Dideklarasikan sebagai 'abstract class' sehingga tidak dapat diinstansiasi 
 *      langsung menggunakan kata kunci 'new Peserta()'.
 *    - Memiliki abstract method 'displayInfo()' yang bertindak sebagai kontrak 
 *      wajib bagi setiap subclass turunan untuk mengimplementasikannya.
 * ============================================================================
 * 
 * Class abstrak Peserta bertindak sebagai superclass untuk seluruh jenis peserta.
 */
public abstract class Peserta {

    // Atribut privat (Menerapkan Enkapsulasi)
    private String id;
    private String nama;
    private String asalArea; // Contoh: RT 02 / Dusun Krajan
    private String kontak;

    /**
     * Konstruktor berparameter untuk inisialisasi data umum peserta.
     * 
     * @param id ID unik peserta
     * @param nama Nama peserta perorangan atau nama regu
     * @param asalArea Asal wilayah/area (RT/RW/Dusun)
     * @param kontak Nomor telepon / kontak peserta
     */
    public Peserta(String id, String nama, String asalArea, String kontak) {
        this.id = id;
        this.nama = nama;
        this.asalArea = asalArea;
        this.kontak = kontak;
    }

    // ========================================================================
    // GETTER & SETTER (Menerapkan Enkapsulasi)
    // ========================================================================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAsalArea() {
        return asalArea;
    }

    public void setAsalArea(String asalArea) {
        this.asalArea = asalArea;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    /**
     * Menerapkan Abstraksi (Abstract Method):
     * Method ini tidak memiliki tubuh/body di superclass. Subclass wajib memberikan
     * implementasi konkrit (overriding) sesuai dengan kebutuhan jenis peserta.
     */
    public abstract void displayInfo();
}
