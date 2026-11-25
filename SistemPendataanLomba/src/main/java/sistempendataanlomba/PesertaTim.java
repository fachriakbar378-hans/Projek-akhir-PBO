package sistempendataanlomba;

/**
 * ============================================================================
 * PENERAPAN PRINSIP OOP:
 * 
 * 1. PEWARISAN (Inheritance):
 *    - Menggunakan 'extends Peserta' untuk mewarisi identitas dasar (id, nama, 
 *      asalArea, kontak) dari superclass.
 * 
 * 2. ENKAPSULASI (Encapsulation):
 *    - Memiliki atribut tambahan 'jumlahAnggota' dengan akses 'private'.
 *    - Dilengkapi dengan getter dan setter khusus untuk memvalidasi/mengakses jumlahAnggota.
 * 
 * 3. POLIMORFISME (Polymorphism - Method Overriding):
 *    - Meng-override method 'displayInfo()' dengan menampilkan atribut khusus tim
 *      (yaitu jumlah anggota kelompok).
 * ============================================================================
 * 
 * Subclass PesertaTim merepresentasikan kelompok/regu perlombaan.
 */
public class PesertaTim extends Peserta {

    // Atribut spesifik untuk tim (Menerapkan Enkapsulasi)
    private int jumlahAnggota;

    /**
     * Konstruktor PesertaTim.
     * Menginisialisasi atribut turunan via super() dan atribut khusus jumlahAnggota.
     * 
     * @param id ID unik tim
     * @param nama Nama regu / tim
     * @param asalArea Wilayah asal regu (RT/Dusun)
     * @param kontak Nomor telepon kontak perwakilan / ketua regu
     * @param jumlahAnggota Banyaknya orang dalam satu regu
     */
    public PesertaTim(String id, String nama, String asalArea, String kontak, int jumlahAnggota) {
        super(id, nama, asalArea, kontak);
        this.jumlahAnggota = jumlahAnggota;
    }

    // ========================================================================
    // GETTER & SETTER (Menerapkan Enkapsulasi)
    // ========================================================================

    public int getJumlahAnggota() {
        return jumlahAnggota;
    }

    public void setJumlahAnggota(int jumlahAnggota) {
        this.jumlahAnggota = jumlahAnggota;
    }

    /**
     * Menerapkan Polimorfisme (Overriding):
     * Implementasi spesifik displayInfo() untuk kategori tim/regu.
     */
    @Override
    public void displayInfo() {
        System.out.println("ID Peserta     : " + getId());
        System.out.println("Nama Tim       : " + getNama());
        System.out.println("Kategori       : Tim / Regu");
        System.out.println("Jumlah Anggota : " + jumlahAnggota + " orang");
        System.out.println("Asal Area      : " + getAsalArea());
        System.out.println("Kontak Ketua   : " + getKontak());
    }
}
