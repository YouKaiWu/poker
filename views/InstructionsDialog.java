package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructionsDialog extends JDialog {
    public InstructionsDialog(JFrame parent) {
        super(parent, "Game Instructions", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        JTabbedPane tabbedPane = new JTabbedPane();

        // 添加遊戲流程頁面
        JTextArea gameFlowTextArea = new JTextArea();
        gameFlowTextArea.setText("遊戲流程：\n\n" +
                "1. 發牌：每個玩家獲得兩張底牌。\n" +
                "2. 底牌下注（Pre-flop）：發完底牌後進行第一輪下注。\n" +
                "3. 翻牌（Flop）：發三張公共牌，然後進行第二輪下注。\n" +
                "4. 轉牌（Turn）：發第四張公共牌，然後進行第三輪下注。\n" +
                "5. 河牌（River）：發第五張公共牌，然後進行最後一輪下注。\n" +
                "6. 攤牌（Showdown）：如果最後一輪下注後還有多於一名玩家，則進行攤牌。\n");
        gameFlowTextArea.setEditable(false);
        JScrollPane gameFlowScrollPane = new JScrollPane(gameFlowTextArea);
        tabbedPane.addTab("遊戲流程", gameFlowScrollPane);

        // 添加大小盲及下注回合頁面
        JTextArea bettingRoundsTextArea = new JTextArea();
        bettingRoundsTextArea.setText("大小盲說明及下注回合：\n\n" +
                "1. 大小盲說明：\n" +
                "   - 小盲注（Small Blind）：由莊家左邊第一位玩家支付。\n" +
                "   - 大盲注（Big Blind）：由小盲注左邊第一位玩家支付。\n\n" +
                "2. 下注回合：\n" +
                "   - 每局有四個下注回合：底牌下注（Pre-flop）、翻牌（Flop）、轉牌（Turn）、河牌（River）。\n" +
                "   - 在每個下注回合，玩家可以選擇以下動作：\n" +
                "     - Check（過牌）：如果沒有前置賭注，玩家可以選擇不下注並保留牌權。\n" +
                "     - Bet（下注）：如果沒有前置賭注，玩家可以選擇下注。\n" +
                "     - Call（跟注）：如果有前置賭注，玩家可以選擇匹配前置賭注。\n" +
                "     - Raise（加注）：如果有前置賭注，玩家可以選擇增加賭注。\n" +
                "     - Fold（棄牌）：玩家選擇放棄本局遊戲，不再參與後續下注。\n");
        bettingRoundsTextArea.setEditable(false);
        JScrollPane bettingRoundsScrollPane = new JScrollPane(bettingRoundsTextArea);
        tabbedPane.addTab("大小盲及下注回合", bettingRoundsScrollPane);

        // 添加牌型頁面
        JTextArea handRanksTextArea = new JTextArea();
        handRanksTextArea.setText("牌型(由大到小)：\n\n" +
                "1. 皇家同花順（Royal Flush）：A、K、Q、J、10，同花色。\n" +
                "2. 同花順（Straight Flush）：五張連續的同花色牌。\n" +
                "3. 四條（Four of a Kind）：四張相同點數的牌。\n" +
                "4. 滿堂紅（Full House）：三張相同點數的牌加一對。\n" +
                "5. 同花（Flush）：五張同花色的牌。\n" +
                "6. 順子（Straight）：五張連續的牌，不同花色。\n" +
                "7. 三條（Three of a Kind）：三張相同點數的牌。\n" +
                "8. 兩對（Two Pair）：兩個不同點數的對子。\n" +
                "9. 一對（One Pair）：一對相同點數的牌。\n" +
                "10. 高牌（High Card）：任何不符合上述牌型的牌，以最高單牌比較大小。\n");
        handRanksTextArea.setEditable(false);
        JScrollPane handRanksScrollPane = new JScrollPane(handRanksTextArea);
        tabbedPane.addTab("牌型", handRanksScrollPane);

        add(tabbedPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
