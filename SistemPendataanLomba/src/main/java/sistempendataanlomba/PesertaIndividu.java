package sistempendataanlomba;

/**
 * ============================================================================
 * PENERAPAN PRINSIP OOP:
 * 
 * 1. PEWARISAN (Inheritance):
 *    - Menggunakan klausa 'extends Peserta' untuk mewarisi seluruh atribut 
 *      dan method yang dimiliki superclass Peserta.
 *    - Memanggil konstruktor superclass menggunakan kata kunci 'super(...)'.
 * 
 * 2. POLIMORFISME (Polymorphism - Method Overriding):
 *    - Meng-override method abstrak 'displayInfo()' dari superclass Peserta 
 *      untuk menampilkan format informasi khusus peserta perorangan.
 * ============================================================================
 * 
 * Subclass PesertaIndividu merepresentasikan peserta perseorangan/tunggal.
 */
public class PesertaIndividu extends Peserta {

    /**
     * Konstruktor PesertaIndividu.
     * Mengalirkan nilai ke konstruktor superclass melalui super().
     * 
     * @param id ID unik peserta
     * @param nama Nama lengkap individu
     * @param asalArea Wilayah asal (misal RT/Dusun)
     * @param kontak Nomor telepon
     */
    public PesertaIndividu(String id, String nama, String asalArea, String kontak) {
        super(id, nama, asalArea, kontak);
    }

    /**
     * Menerapkan Polimorfisme (Overriding):
     * Implementasi spesifik displayInfo() untuk peserta kategori individu.
     */
    @Override
    public void displayInfo() {
        System.out.println("ID Peserta  : " + getId());
        System.out.println("Nama        : " + getNama());
        System.out.println("Kategori    : Individu (Perorangan)");
        System.out.println("Asal Area   : " + getAsalArea());
        System.out.println("Kontak      : " + getKontak());
    }
}
