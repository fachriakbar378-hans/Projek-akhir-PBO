package sistempendataanlomba;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ============================================================================
 * PENERAPAN PRINSIP OOP PADA MAIN CLASS:
 * 
 * 1. POLIMORFISME (Polymorphic Collection & Dynamic Binding):
 *    - 'ArrayList<Peserta> daftarPeserta' menampung objek PesertaIndividu maupun 
 *      PesertaTim dalam satu wadah seragam (Polymorphic Collection).
 *    - Saat menampilkan data peserta, pemanggilan 'peserta.displayInfo()' secara
 *      otomatis mengeksekusi versi method yang tepat berdasarkan tipe objek aslinya.
 * 
 * 2. ENKAPSULASI:
 *    - Seluruh interaksi terhadap atribut objek (seperti membaca ID, nama, kuota)
 *      dilakukan melalui getter dan setter.
 * 
 * 3. ABSTRAKSI:
 *    - Logika program berinteraksi dengan tipe abstrak 'Peserta' tanpa harus terikat
 *      pada implementasi teknis internal subclass.
 * ============================================================================
 * 
 * Class Main menyediakan antarmuka baris perintah (CLI) untuk mengelola
 * Sistem Pendataan Lomba menggunakan struktur memori ArrayList.
 */
public class Main {

    // Penyimpanan data in-memory menggunakan ArrayList
    private static ArrayList<Lomba> daftarLomba = new ArrayList<>();
    private static ArrayList<Peserta> daftarPeserta = new ArrayList<>();
    private static ArrayList<Pendaftaran> daftarPendaftaran = new ArrayList<>();

    // Scanner tunggal untuk input CLI
    private static Scanner scanner = new Scanner(System.in);
    private static int regCounter = 1; // Counter untuk ID pendaftaran otomatis

    public static void main(String[] args) {
        // Inisialisasi data awal sebagai contoh (dapat langsung diuji)
        inisialisasiDataAwal();

        boolean berjalan = true;
        while (berjalan) {
            tampilkanMenu();
            System.out.print("Pilih opsi menu (0-5): ");
            String pilihan = scanner.nextLine().trim();

            switch (pilihan) {
                case "1":
                    tambahLomba();
                    break;
                case "2":
                    registrasiPeserta();
                    break;
                case "3":
                    daftarkanPesertaKeLomba();
                    break;
                case "4":
                    lihatDaftarPesertaPerLomba();
                    break;
                case "5":
                    lihatSemuaData();
                    break;
                case "0":
                    System.out.println("\nTerima kasih telah menggunakan Sistem Pendataan Lomba!");
                    berjalan = false;
                    break;
                default:
                    System.out.println("\n[Peringatan] Pilihan tidak valid. Silakan coba lagi.");
            }
            
            if (berjalan) {
                System.out.print("\nTekan [ENTER] untuk kembali ke menu utama...");
                scanner.nextLine();
            }
        }
    }

    /**
     * Menampilkan menu utama aplikasi.
     */
    private static void tampilkanMenu() {
        System.out.println("\n==================================================");
        System.out.println("          SISTEM PENDATAAN LOMBA (CLI)");
        System.out.println("==================================================");
        System.out.println("1. Tambah Lomba (Create Event)");
        System.out.println("2. Registrasi Peserta (Individu / Tim)");
        System.out.println("3. Daftarkan Peserta ke Cabang Lomba");
        System.out.println("4. Lihat Daftar Peserta per Lomba");
        System.out.println("5. Lihat Ringkasan Seluruh Data (Lomba & Peserta)");
        System.out.println("0. Keluar");
        System.out.println("==================================================");
    }

    /**
     * Operasi 1: Tambah Lomba Baru.
     */
    private static void tambahLomba() {
        System.out.println("\n--- [TAMBAH LOMBA BARU] ---");
        System.out.print("Masukkan ID Lomba (contoh: L01, L02): ");
        String idLomba = scanner.nextLine().trim();

        if (cariLombaById(idLomba) != null) {
            System.out.println("[Gagal] ID Lomba '" + idLomba + "' sudah digunakan!");
            return;
        }

        System.out.print("Masukkan Nama Lomba (contoh: Jalan Santai, Futsal): ");
        String namaLomba = scanner.nextLine().trim();

        int kuota = 0;
        while (true) {
            try {
                System.out.print("Masukkan Kuota Maksimal: ");
                kuota = Integer.parseInt(scanner.nextLine().trim());
                if (kuota <= 0) {
                    System.out.println("[Peringatan] Kuota harus lebih besar dari 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("[Peringatan] Harap masukkan angka yang valid untuk kuota!");
            }
        }

        Lomba lombaBaru = new Lomba(idLomba, namaLomba, kuota);
        daftarLomba.add(lombaBaru);
        System.out.println("[Berhasil] Lomba '" + namaLomba + "' berhasil ditambahkan!");
    }

    /**
     * Operasi 2: Registrasi Peserta Baru (Individu atau Tim).
     * Menerapkan Abstraksi dan Pewarisan saat membuat objek instance dari subclass Peserta.
     */
    private static void registrasiPeserta() {
        System.out.println("\n--- [REGISTRASI PESERTA BARU] ---");
        System.out.println("Pilih Jenis Kepesertaan:");
        System.out.println("1. Peserta Individu");
        System.out.println("2. Peserta Tim / Regu");
        System.out.print("Pilihan (1/2): ");
        String jenis = scanner.nextLine().trim();

        if (!jenis.equals("1") && !jenis.equals("2")) {
            System.out.println("[Gagal] Pilihan jenis peserta tidak valid!");
            return;
        }

        System.out.print("Masukkan ID Peserta (contoh: P01, P02): ");
        String id = scanner.nextLine().trim();

        if (cariPesertaById(id) != null) {
            System.out.println("[Gagal] ID Peserta '" + id + "' sudah terdaftar!");
            return;
        }

        System.out.print("Masukkan Nama (Nama Peserta / Nama Tim): ");
        String nama = scanner.nextLine().trim();

        System.out.print("Masukkan Asal Area (contoh: RT 01, Dusun Krajan): ");
        String asalArea = scanner.nextLine().trim();

        System.out.print("Masukkan Kontak (No. HP / WA): ");
        String kontak = scanner.nextLine().trim();

        // Variabel bertipe superclass Peserta (Polymorphic Reference)
        Peserta pesertaBaru;

        if (jenis.equals("1")) {
            // Instansiasi PesertaIndividu
            pesertaBaru = new PesertaIndividu(id, nama, asalArea, kontak);
            daftarPeserta.add(pesertaBaru);
            System.out.println("[Berhasil] Peserta Individu '" + nama + "' berhasil didaftarkan!");
        } else {
            // Instansiasi PesertaTim
            int jumlahAnggota = 0;
            while (true) {
                try {
                    System.out.print("Masukkan Jumlah Anggota Tim: ");
                    jumlahAnggota = Integer.parseInt(scanner.nextLine().trim());
                    if (jumlahAnggota < 2) {
                        System.out.println("[Peringatan] Jumlah anggota tim minimal 2 orang!");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("[Peringatan] Masukkan angka yang valid!");
                }
            }

            pesertaBaru = new PesertaTim(id, nama, asalArea, kontak, jumlahAnggota);
            daftarPeserta.add(pesertaBaru);
            System.out.println("[Berhasil] Peserta Tim '" + nama + "' berhasil didaftarkan!");
        }
    }

    /**
     * Operasi 3: Mendaftarkan Peserta ke Cabang Lomba (Transaksi Pendaftaran).
     * Mengecek ketersediaan lomba, kuota pendaftaran, dan mendeteksi duplikasi.
     */
    private static void daftarkanPesertaKeLomba() {
        System.out.println("\n--- [DAFTARKAN PESERTA KE LOMBA] ---");

        if (daftarPeserta.isEmpty()) {
            System.out.println("[Peringatan] Belum ada peserta terdaftar. Silakan registrasi peserta terlebih dahulu.");
            return;
        }

        if (daftarLomba.isEmpty()) {
            System.out.println("[Peringatan] Belum ada lomba tersedia. Silakan tambahkan lomba terlebih dahulu.");
            return;
        }

        // Tampilkan daftar singkat ID peserta
        System.out.println("Daftar Peserta Tersedia:");
        for (Peserta p : daftarPeserta) {
            System.out.println(" - " + p.getId() + " : " + p.getNama() + " (" + (p instanceof PesertaIndividu ? "Individu" : "Tim") + ")");
        }
        System.out.print("Masukkan ID Peserta: ");
        String idPeserta = scanner.nextLine().trim();
        Peserta peserta = cariPesertaById(idPeserta);

        if (peserta == null) {
            System.out.println("[Gagal] Peserta dengan ID '" + idPeserta + "' tidak ditemukan!");
            return;
        }

        // Tampilkan daftar singkat ID lomba
        System.out.println("\nDaftar Lomba Tersedia:");
        for (Lomba l : daftarLomba) {
            int terdaftar = hitungPesertaTerdaftar(l.getIdLomba());
            System.out.println(" - " + l.getIdLomba() + " : " + l.getNamaLomba() + " [Terisi: " + terdaftar + "/" + l.getKuota() + "]");
        }
        System.out.print("Masukkan ID Lomba: ");
        String idLomba = scanner.nextLine().trim();
        Lomba lomba = cariLombaById(idLomba);

        if (lomba == null) {
            System.out.println("[Gagal] Lomba dengan ID '" + idLomba + "' tidak ditemukan!");
            return;
        }

        // Validasi apakah peserta sudah terdaftar di lomba tersebut
        if (apakahSudahTerdaftar(peserta.getId(), lomba.getIdLomba())) {
            System.out.println("[Gagal] Peserta '" + peserta.getNama() + "' sudah terdaftar pada lomba '" + lomba.getNamaLomba() + "'!");
            return;
        }

        // Validasi kuota lomba
        int terisi = hitungPesertaTerdaftar(lomba.getIdLomba());
        if (terisi >= lomba.getKuota()) {
            System.out.println("[Gagal] Kuota untuk lomba '" + lomba.getNamaLomba() + "' sudah penuh (" + lomba.getKuota() + " peserta)!");
            return;
        }

        // Buat transaksi pendaftaran baru
        String regId = String.format("REG-%03d", regCounter++);
        String tanggal = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        Pendaftaran pendaftaran = new Pendaftaran(regId, peserta, lomba, tanggal);
        daftarPendaftaran.add(pendaftaran);

        System.out.println("\n[Sukses] Peserta berhasil didaftarkan ke lomba!");
        System.out.println("ID Registrasi  : " + regId);
        System.out.println("Nama Peserta   : " + peserta.getNama());
        System.out.println("Cabang Lomba   : " + lomba.getNamaLomba());
        System.out.println("Sisa Kuota     : " + (lomba.getKuota() - (terisi + 1)));
    }

    /**
     * Operasi 4: Melihat Daftar Peserta per Lomba.
     * Menerapkan Polimorfisme: method 'peserta.displayInfo()' dipanggil dari objek transaksi.
     */
    private static void lihatDaftarPesertaPerLomba() {
        System.out.println("\n--- [DAFTAR PESERTA PER LOMBA] ---");

        if (daftarLomba.isEmpty()) {
            System.out.println("[Peringatan] Belum ada data lomba yang tersimpan.");
            return;
        }

        System.out.println("Pilih Cabang Lomba:");
        for (int i = 0; i < daftarLomba.size(); i++) {
            Lomba l = daftarLomba.get(i);
            System.out.println((i + 1) + ". " + l.getNamaLomba() + " (ID: " + l.getIdLomba() + ")");
        }
        System.out.print("Masukkan ID Lomba yang ingin dilihat: ");
        String idLomba = scanner.nextLine().trim();

        Lomba lombaDipilih = cariLombaById(idLomba);
        if (lombaDipilih == null) {
            System.out.println("[Gagal] Lomba dengan ID '" + idLomba + "' tidak ditemukan!");
            return;
        }

        int totalTerdaftar = hitungPesertaTerdaftar(idLomba);
        System.out.println("\n==================================================");
        System.out.println("DATA PESERTA LOMBA: " + lombaDipilih.getNamaLomba().toUpperCase());
        System.out.println("ID Lomba : " + lombaDipilih.getIdLomba());
        System.out.println("Status   : Terisi " + totalTerdaftar + " dari kuota " + lombaDipilih.getKuota());
        System.out.println("==================================================");

        if (totalTerdaftar == 0) {
            System.out.println("Belum ada peserta yang mendaftar pada cabang lomba ini.");
            return;
        }

        int no = 1;
        for (Pendaftaran p : daftarPendaftaran) {
            if (p.getLomba().getIdLomba().equalsIgnoreCase(idLomba)) {
                System.out.println("No. " + no);
                System.out.println("Kode Registrasi : " + p.getIdPendaftaran());
                System.out.println("Tanggal Daftar  : " + p.getTanggalDaftar());
                
                // Menerapkan Polimorfisme Dinamis:
                // JVM otomatis memilih displayInfo() milik PesertaIndividu atau PesertaTim
                p.getPeserta().displayInfo();
                System.out.println("--------------------------------------------------");
                no++;
            }
        }
    }

    /**
     * Operasi 5: Melihat ringkasan seluruh data lomba dan peserta yang terdaftar di sistem.
     */
    private static void lihatSemuaData() {
        System.out.println("\n==================================================");
        System.out.println("          RINGKASAN DATA SISTEM LOMBA");
        System.out.println("==================================================");

        System.out.println("\n[1] DAFTAR SEMUA CABANG LOMBA (" + daftarLomba.size() + " Lomba):");
        if (daftarLomba.isEmpty()) {
            System.out.println("  (Belum ada data lomba)");
        } else {
            for (Lomba l : daftarLomba) {
                int terdaftar = hitungPesertaTerdaftar(l.getIdLomba());
                System.out.println("  - [" + l.getIdLomba() + "] " + l.getNamaLomba() 
                        + " | Kuota: " + l.getKuota() + " | Terisi: " + terdaftar);
            }
        }

        System.out.println("\n[2] DAFTAR SEMUA PESERTA TERDAFTAR (" + daftarPeserta.size() + " Peserta):");
        if (daftarPeserta.isEmpty()) {
            System.out.println("  (Belum ada data peserta)");
        } else {
            for (Peserta p : daftarPeserta) {
                System.out.println("  --------------------------------------");
                // Pemanggilan method polimorfis
                p.displayInfo();
            }
            System.out.println("  --------------------------------------");
        }
    }

    // ========================================================================
    // HELPER METHODS (Metode Pembantu)
    // ========================================================================

    private static Lomba cariLombaById(String id) {
        for (Lomba l : daftarLomba) {
            if (l.getIdLomba().equalsIgnoreCase(id)) {
                return l;
            }
        }
        return null;
    }

    private static Peserta cariPesertaById(String id) {
        for (Peserta p : daftarPeserta) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    private static int hitungPesertaTerdaftar(String idLomba) {
        int count = 0;
        for (Pendaftaran p : daftarPendaftaran) {
            if (p.getLomba().getIdLomba().equalsIgnoreCase(idLomba)) {
                count++;
            }
        }
        return count;
    }

    private static boolean apakahSudahTerdaftar(String idPeserta, String idLomba) {
        for (Pendaftaran p : daftarPendaftaran) {
            if (p.getPeserta().getId().equalsIgnoreCase(idPeserta) && 
                p.getLomba().getIdLomba().equalsIgnoreCase(idLomba)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Mempersiapkan contoh data awal agar sistem langsung dapat dicoba
     * tanpa perlu menginput dari awal secara manual.
     */
    private static void inisialisasiDataAwal() {
        // Sample Lomba
        daftarLomba.add(new Lomba("L01", "Jalan Santai", 50));
        daftarLomba.add(new Lomba("L02", "Futsal Antar RT", 8));
        daftarLomba.add(new Lomba("L03", "Catur Cepat", 16));

        // Sample Peserta (Polimorfisme: objek turunan dimasukkan ke list bertipe Peserta)
        Peserta p1 = new PesertaIndividu("P01", "Budi Santoso", "RT 01 / RW 02", "081234567890");
        Peserta p2 = new PesertaTim("P02", "Garuda Muda FC", "RT 03 / RW 01", "081987654321", 7);
        Peserta p3 = new PesertaIndividu("P03", "Siti Aminah", "RT 02 / RW 02", "082211334455");

        daftarPeserta.add(p1);
        daftarPeserta.add(p2);
        daftarPeserta.add(p3);

        // Sample Transaksi Pendaftaran
        daftarPendaftaran.add(new Pendaftaran("REG-001", p1, daftarLomba.get(0), "11-11-2026"));
        daftarPendaftaran.add(new Pendaftaran("REG-002", p2, daftarLomba.get(1), "11-11-2026"));
        regCounter = 3;
    }
}
