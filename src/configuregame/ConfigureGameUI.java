package configuregame;

import startgame.SnakeGameApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import startgame.StartGameService;

import static configuregame.SkinManager.getColors;

public class ConfigureGameUI extends JFrame {

    // =========================
    // UI COMPONENTS
    // =========================
    private JButton btnStart;
    private JComboBox<String> cbMusic, cbDifficulty, cbSkinP1, cbSkinP2;
    private JRadioButton rdSingle, rdMulti;
    private JCheckBox chkSound, chkMusic;
    private JPanel pnSound, pnSkin;

    // =========================
    // CONTROLLER
    // =========================
    private final GameConfigController controller;

    public ConfigureGameUI() {
        controller = new GameConfigController();
        initComponents();
        setupLayout();
        setupEvents();
        setTitle("Snake Game Configuration");
        setSize(550, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    // =========================
    // INIT COMPONENTS
    // =========================
    private void initComponents() {

        btnStart = new JButton("Start Game");
        btnStart.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnStart.setBackground(new Color(238, 82, 82));
        btnStart.setForeground(new Color(255, 255, 255));
        btnStart.setFocusPainted(false);
        btnStart.setBorderPainted(false);
        btnStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnStart.setBackground(new Color(238, 82, 82));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnStart.setBackground(new Color(175, 29, 29));
            }
        });
        // Difficulty
        cbDifficulty = new JComboBox<>();
        cbDifficulty.addItem("Easy");
        cbDifficulty.addItem("Medium");
        cbDifficulty.addItem("Hard");

        // Skin
        cbSkinP1 = new JComboBox<>();
        cbSkinP2 = new JComboBox<>();

        String[] skins = {
                "Classic Green",
                "Blue Snake",
                "White Snake",
                "Red Snake",
                "Purple Snake"
        };

        for (String skin : skins) {

            cbSkinP1.addItem(skin);
            cbSkinP2.addItem(skin);
        }

        cbSkinP2.setSelectedItem("Blue Snake");

        JPanel pnPlayer1 = new JPanel(
                new BorderLayout(5, 0)
        );

        pnPlayer1.add(
                new JLabel("Player 1"),
                BorderLayout.WEST
        );

        pnPlayer1.add(
                cbSkinP1,
                BorderLayout.CENTER
        );

        JPanel pnPlayer2 = new JPanel(
                new BorderLayout(5, 0)
        );

        pnPlayer2.add(
                new JLabel("Player 2"),
                BorderLayout.WEST
        );

        pnPlayer2.add(
                cbSkinP2,
                BorderLayout.CENTER
        );

        pnSkin = new JPanel();

        pnSkin.setLayout(
                new BoxLayout(
                        pnSkin,
                        BoxLayout.Y_AXIS
                )
        );
        pnSkin.setPreferredSize(
                new Dimension(250, 70)
        );
        pnSkin.add(pnPlayer1);

        pnSkin.add(Box.createVerticalStrut(5));

        pnSkin.add(pnPlayer2);
        // =====================
// SOUND
// =====================
        chkSound = new JCheckBox("Sound");
        chkMusic = new JCheckBox("Music");

        chkSound.setSelected(true);
        chkMusic.setSelected(true);

        pnSound = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnSound.add(chkSound);
        pnSound.add(chkMusic);

        //
        rdSingle = new JRadioButton("1 Player");
        rdMulti = new JRadioButton("2 Players");

        rdSingle.setSelected(true);
        cbSkinP2.setEnabled(false);
        // =====================
        // BACKGROUND MUSIC
        // =====================
        cbMusic = new JComboBox<>();

        cbMusic.addItem("Background 1");
        cbMusic.addItem("Background 2");
        cbMusic.addItem("Background 3");
        ButtonGroup group = new ButtonGroup();
        group.add(rdSingle);
        group.add(rdMulti);
    }

    // =========================
    // LAYOUT
    // =========================
    private void setupLayout() {
        setLayout(new BorderLayout(5, 5));
        JLabel lbNameGame = new JLabel("Snake");
        lbNameGame.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lbNameGame.setForeground(new Color(12, 227, 20, 124));
        JPanel pnTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnTop.add(lbNameGame);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 15));

        panel.setBorder(BorderFactory.createEmptyBorder(
                20,
                20,
                20,
                20
        ));

        // Difficulty
        panel.add(new JLabel("Difficulty:"));
        panel.add(cbDifficulty);

        // Skin
        panel.add(new JLabel("Snake Skin:"));
        panel.add(pnSkin);

        // Sound
        panel.add(new JLabel("Sound:"));
        panel.add(pnSound);

        // Music
        panel.add(new JLabel("Background Music:"));
        panel.add(cbMusic);

        // Start button
        panel.add(new JLabel());
        panel.add(btnStart);

        panel.add(new JLabel("Game Mode:"));

        JPanel modePanel = new JPanel();
        modePanel.add(rdSingle);
        modePanel.add(rdMulti);

        panel.add(modePanel);
        add(pnTop, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    // =========================
    // EVENTS
    // =========================
    private void setupEvents() {
        rdSingle.addActionListener(e -> {

            cbSkinP2.setEnabled(false);
        });

        rdMulti.addActionListener(e -> {

            cbSkinP2.setEnabled(true);
        });
        btnStart.addActionListener(e -> {
            // ===================== // GET DIFFICULTY // ====================
            String difficulty = (String) cbDifficulty.getSelectedItem();
            int delay = switch (difficulty) {
                case "Easy" -> 150;
                case "Medium" -> 100;
                case "Hard" -> 60;
                default -> 120;
            };
            // ===================== // GET SKIN // =====================
            String skinP1 = (String) cbSkinP1.getSelectedItem();
            String skinP2 = (String) cbSkinP2.getSelectedItem();
            // ===================== // GET MUSIC // =====================
            String music = (String) cbMusic.getSelectedItem();
            // ===================== // GET SOUND // =====================
            boolean soundEnabled = chkSound.isSelected();
            boolean musicEnabled = chkMusic.isSelected();
            boolean multiplayer = rdMulti.isSelected();
            // ===================== // CONFIG GAME // =====================
            controller.setDifficulty(delay);
            controller.setSound(soundEnabled);
            controller.setMusic(musicEnabled);
            controller.setBackgroundMusic(music);
            controller.setMultiplayer(multiplayer);
            // ===================== // PLAYER 1 SKIN // =====================
            Color[] p1Colors = getColors(skinP1);
            controller.setPlayer1Skin(p1Colors[0], p1Colors[1]);
            // ===================== // PLAYER 2 SKIN // =====================
            if (multiplayer) {
                Color[] p2Colors = getColors(skinP2);
                controller.setPlayer2Skin(p2Colors[0], p2Colors[1]);
            }
            // ===================== // START GAME // =====================
            StartGameService service = new StartGameService();
            service.start(controller.getConfig());
            dispose();
        });
    }

}

