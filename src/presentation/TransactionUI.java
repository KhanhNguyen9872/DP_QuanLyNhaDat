package presentation;

import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import domain.model.*;
import observer.Subscriber;
import presentation.commandprocessor.*;
import domain.TransactionService;

public class TransactionUI extends JFrame implements Subscriber, ActionListener, ListSelectionListener {
    private TransactionService transactionService = null;

    private JMenuBar jMenuBar = null;
    private DefaultTableModel tableModel = null;
    private JTable transactionTable = null;
    private JButton addButton, updateButton, deleteButton, findIDButton, sumButton, sumCountHouseButton, sumCountLandButton, avgHouseButton, avgLandButton, exportButton;
    private JLabel labelMaGiaoDich, labelNgayGiaoDich, labelDonGia, labelLoaiNha, labelDiaChi, labelDienTich, labelTimKiem, labelMonth, labelLoaiDat;
    private JTextField textMaGiaoDich, textDonGia, textDiaChi, textDienTich, textTimKiem;
    private JComboBox comboBoxLoaiNha, comboBoxLoaiDat, comboBoxMonth;
    private JXDatePicker jngayGiaoDich;
    private CommandProcessor commandProcessor;

    public TransactionUI(TransactionService transactionService, CommandProcessor commandProcessor) {
        transactionService.addSub(this);
        this.transactionService = transactionService;
        this.commandProcessor = commandProcessor;
        this.buildMenuBar();
        this.buildPanel();

        setSize(1200, 640);
        setTitle("Quản lý giao dịch nhà đất");
        setJMenuBar(this.jMenuBar);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reloadTable();
        clearInput();
        setVisible(true);
    }

    public void buildMenuBar() {
        this.jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("Hệ thống");
        JMenuItem jMenuItemClose = new JMenuItem("Đóng");

        jMenuItemClose.addActionListener(this);

        jMenu.add(jMenuItemClose);
        this.jMenuBar.add(jMenu);
    }

    public void buildPanel() {
        // Initialize label, textfield, button
        this.labelMaGiaoDich = new JLabel("Mã giao dịch: ");
        this.labelNgayGiaoDich = new JLabel("Ngày giao dịch: ");
        this.labelDonGia = new JLabel("Đơn giá: ");
        this.labelLoaiNha = new JLabel("Loại nhà: ");
        this.labelDiaChi = new JLabel("Địa chỉ: ");
        this.labelDienTich = new JLabel("Diện tích: ");
        this.labelTimKiem = new JLabel("Tìm kiếm: ");
        this.labelMonth = new JLabel("Tháng: ");
        this.labelLoaiDat = new JLabel("Loại đất: ");

        this.jngayGiaoDich = new JXDatePicker();
        this.jngayGiaoDich.setDate(Calendar.getInstance().getTime());
        this.jngayGiaoDich.setFormats(new SimpleDateFormat("yyyy/MM/dd"));

        this.textMaGiaoDich = new JTextField(10);
        this.textDonGia = new JTextField(10);
        this.textDiaChi = new JTextField(20);
        this.textDienTich = new JTextField(10);
        this.textTimKiem = new JTextField(20);
        
        String[] lstHouse = {"Thường", "Cao cấp" };
        this.comboBoxLoaiNha = new JComboBox<>(lstHouse);

        String[] lstLand = {"A", "B", "C" };
        this.comboBoxLoaiDat = new JComboBox<>(lstLand);

        String[] lstMonth = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
        this.comboBoxMonth = new JComboBox<>(lstMonth);

        this.addButton = new JButton("Thêm");
        this.updateButton = new JButton("Sửa");
        this.deleteButton = new JButton("Xóa");
        this.findIDButton = new JButton("Tìm theo ID");
        this.sumButton = new JButton("Thành tiền");
        this.sumCountHouseButton = new JButton("Tổng loại nhà");
        this.sumCountLandButton = new JButton("Tổng loại đất");
        this.avgHouseButton = new JButton("Trung bình TT nhà");
        this.avgLandButton = new JButton("Trung bình TT đất");
        this.exportButton = new JButton("Xuất giao dịch");

        // Initialize table
        String[] columnNames = { "MaGiaoDich", "NgayGiaoDich", "DonGia", "LoaiDat", "LoaiNha", "DiaChi", "DienTich" };
        tableModel = new DefaultTableModel(columnNames, 0);
        transactionTable = new JTable(tableModel);
        transactionTable.getSelectionModel().addListSelectionListener(this);

        // add ActionListener
        this.addButton.addActionListener(this);
        this.updateButton.addActionListener(this);
        this.deleteButton.addActionListener(this);
        this.findIDButton.addActionListener(this);
        this.sumButton.addActionListener(this);
        this.sumCountHouseButton.addActionListener(this);
        this.sumCountLandButton.addActionListener(this);
        this.avgHouseButton.addActionListener(this);
        this.avgLandButton.addActionListener(this);
        this.exportButton.addActionListener(this);

        //
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        inputPanel.add(this.labelMaGiaoDich, gbc);
        gbc.gridx++;
        inputPanel.add(this.textMaGiaoDich, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(this.labelNgayGiaoDich, gbc);
        gbc.gridx++;
        inputPanel.add(this.jngayGiaoDich, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(this.labelDonGia, gbc);
        gbc.gridx++;
        inputPanel.add(this.textDonGia, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(this.labelLoaiDat, gbc);
        gbc.gridx++;
        inputPanel.add(this.comboBoxLoaiDat, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(this.labelLoaiNha, gbc);
        gbc.gridx++;
        inputPanel.add(this.comboBoxLoaiNha, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(this.labelDiaChi, gbc);
        gbc.gridx++;
        inputPanel.add(this.textDiaChi, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(this.labelDienTich, gbc);
        gbc.gridx++;
        inputPanel.add(this.textDienTich, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(this.labelTimKiem, gbc);
        gbc.gridx++;
        inputPanel.add(this.textTimKiem, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(this.labelMonth, gbc);
        gbc.gridx++;
        inputPanel.add(this.comboBoxMonth, gbc);

        // find


        // button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(this.addButton);
        buttonPanel.add(this.updateButton);
        buttonPanel.add(this.deleteButton);
        buttonPanel.add(this.findIDButton);
        buttonPanel.add(this.sumButton);
        buttonPanel.add(this.sumCountHouseButton);
        buttonPanel.add(this.sumCountLandButton);
        buttonPanel.add(this.avgHouseButton);
        buttonPanel.add(this.avgLandButton);
        buttonPanel.add(this.exportButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(this.transactionTable), BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        this.add(mainPanel);
    }

    public void reloadTable() {
        List<Transaction> transactions = this.transactionService.getData();
        reloadTable(transactions);
    }

    public void reloadTable(List<Transaction> transactions) {
        tableModel.setRowCount(0); // clear table

        for(Transaction transaction : transactions) {
            if (transaction.getClass().equals(House.class)) {
                Object[] row = { transaction.getMaGiaoDich(), transaction.getNgayGiaoDich(), transaction.getDonGia(), "", transaction.getLoai(), transaction.getDiaChi(), transaction.getDienTich() };
                tableModel.addRow(row);
            } else if (transaction.getClass().equals(Land.class)) {
                Object[] row = { transaction.getMaGiaoDich(), transaction.getNgayGiaoDich(), transaction.getDonGia(), transaction.getLoai(), "", "", transaction.getDienTich() };
                tableModel.addRow(row);
            }
        }
    }

    public void clearInput() {
        this.textMaGiaoDich.setText("");
        this.jngayGiaoDich.setDate(new Date("01/07/2024"));
        this.textDonGia.setText("");
        this.comboBoxLoaiDat.setSelectedIndex(-1);
        this.comboBoxLoaiNha.setSelectedIndex(-1);
        this.textDiaChi.setText("");
        this.textDienTich.setText("");
    }

    public Transaction getCurrentTransaction() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
        // Get data from input

        int maGiaoDich, donGia;
        double dienTich;

        try {
            maGiaoDich = Integer.parseInt(this.textMaGiaoDich.getText());
            donGia = Integer.parseInt(this.textDonGia.getText());
            dienTich = Double.parseDouble(this.textDienTich.getText());
        } catch (Exception ex) {
            return null;
        }

        String ngayGiaoDich = formater.format(this.jngayGiaoDich.getDate());

        String loaiDat;
        if (this.comboBoxLoaiDat.getSelectedItem() != null) {
            loaiDat = this.comboBoxLoaiDat.getSelectedItem().toString();
        } else {
            loaiDat = "";
        }

        String loaiNha;
        if (this.comboBoxLoaiNha.getSelectedItem() != null) {
            loaiNha = this.comboBoxLoaiNha.getSelectedItem().toString();
        } else {
            loaiNha = "";
        }
        
        String diaChi = this.textDiaChi.getText();

        if (!loaiDat.isEmpty()) {
            return new Land(maGiaoDich, ngayGiaoDich, donGia, loaiDat, dienTich);
        }
        return new House(maGiaoDich, ngayGiaoDich, donGia, loaiNha, diaChi, dienTich);
    }

    public Command calcMoney(Transaction transaction) {
        if (transaction == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một giao dịch");
            return null;
        } else {
            if (this.transactionService.findGetTransaction(transaction.getMaGiaoDich()) == null) {
                JOptionPane.showMessageDialog(this, "Mã giao dịch này không tồn tại");
                return null;
            };
        };
        return new CalcCommand(transactionService, transaction);
    }

    public Command addTransaction(Transaction transaction) {
        if (this.transactionService.findGetTransaction(transaction.getMaGiaoDich()) != null) {
            JOptionPane.showMessageDialog(this, "Mã giao dịch này đã tồn tại");
            return null;
        };
        return new AddCommand(transactionService, transaction);
    }

    public Command updateTransaction(Transaction transaction) {
        if (this.transactionService.findGetTransaction(transaction.getMaGiaoDich()) == null) {
            JOptionPane.showMessageDialog(this, "Mã giao dịch này không tồn tại");
            return null;
        };
        return new UpdateCommand(transactionService, transaction);
    }

    public Command deleteTransaction() {
        int index = this.transactionTable.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng dữ liệu nào!");
            return null;
        }
        int maGiaoDich = Integer.parseInt(this.transactionTable.getValueAt(index, 0).toString());
        return new DeleteCommand(transactionService, maGiaoDich);
    }

    public Command sumCountHouse() {
        try {
            return new CountHouseCommand(this.transactionService, this.comboBoxLoaiNha.getSelectedItem().toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại nhà");
        }
        return null;
    }

    public Command sumCountLand() {
        try {
            return new CountLandCommand(this.transactionService, this.comboBoxLoaiDat.getSelectedItem().toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn loại đất");
        }
        return null;
    }

    public Command avgMoneyHouse() {
        return new AvgHouseCommand(this.transactionService);
    }

    public Command avgMoneyLand() {
        return new AvgLandCommand(this.transactionService);
    }

    public Command exportData() {
        try {
            return new ExportCommand(this.transactionService, Integer.parseInt(this.comboBoxMonth.getSelectedItem().toString()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Command findByID() {
        String text = textTimKiem.getText();
        if (text == null || text.isEmpty()) {
            return null;
        }
        int maGiaoDich = Integer.parseInt(text);
        return new FindByIDCommand(transactionService, maGiaoDich);
    }

    public void setTransaction(Transaction transaction) {
        if (transaction.getClass().equals(House.class)) {
            
        }
        String maGiaoDich = String.valueOf(transaction.getMaGiaoDich());
        String ngayGiaoDich = transaction.getNgayGiaoDich();
        String donGia = String.valueOf(transaction.getDonGia());
        String loai = transaction.getLoai();
        String diaChi = transaction.getDiaChi();
        String dienTich = String.valueOf(transaction.getDienTich());

        this.textMaGiaoDich.setText(maGiaoDich);
        this.jngayGiaoDich.setDate(new Date(ngayGiaoDich.replace('-', '/')));
        this.textDonGia.setText(donGia);

        if (transaction.getClass().equals(House.class)) {
            this.comboBoxLoaiDat.setSelectedIndex(-1);

            if (loai.toLowerCase().equals("nhà cao cấp")) {
                this.comboBoxLoaiNha.setSelectedIndex(1);
            } else if (loai.toLowerCase().equals("nhà thường")) {
                this.comboBoxLoaiNha.setSelectedIndex(0);
            } else {
                this.comboBoxLoaiNha.setSelectedIndex(-1);
            }

            this.textDiaChi.setText(diaChi);
        } else if (transaction.getClass().equals(Land.class)) {
            this.comboBoxLoaiNha.setSelectedIndex(-1);

            if (loai.toLowerCase().equals("a")) {
                this.comboBoxLoaiDat.setSelectedIndex(0);
            } else if (loai.toLowerCase().equals("b")) {
                this.comboBoxLoaiDat.setSelectedIndex(1);
            } else if (loai.toLowerCase().equals("c")) {
                this.comboBoxLoaiDat.setSelectedIndex(2);
            } else {
                this.comboBoxLoaiDat.setSelectedIndex(-1);
            }

            this.textDiaChi.setText("");
        }

        this.textDienTich.setText(dienTich);
    }

    public void close() {
        this.dispose();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int row = this.transactionTable.getSelectedRow();

        if (row == -1) {
            return;
        }

        String maGiaoDich = this.transactionTable.getModel().getValueAt(row, 0).toString();
        String ngayGiaoDich = this.transactionTable.getModel().getValueAt(row, 1).toString();
        String donGia = this.transactionTable.getModel().getValueAt(row, 2).toString();
        String loaiDat = this.transactionTable.getModel().getValueAt(row, 3).toString();
        String loaiNha = this.transactionTable.getModel().getValueAt(row, 4).toString();
        String diaChi = this.transactionTable.getModel().getValueAt(row, 5).toString();
        String dienTich = this.transactionTable.getModel().getValueAt(row, 6).toString();

        this.textMaGiaoDich.setText(maGiaoDich);
        this.jngayGiaoDich.setDate(new Date(ngayGiaoDich.replace('-', '/')));
        this.textDonGia.setText(donGia);

        if (loaiDat.toLowerCase().equals("a")) {
            this.comboBoxLoaiDat.setSelectedIndex(0);    
        } else if (loaiDat.toLowerCase().equals("b")) {
            this.comboBoxLoaiDat.setSelectedIndex(1);    
        } else if (loaiDat.toLowerCase().equals("c")) {
            this.comboBoxLoaiDat.setSelectedIndex(2);    
        } else {
            this.comboBoxLoaiDat.setSelectedIndex(-1);
        }
        
        if (loaiNha.toLowerCase().equals("cao cấp")) {
            this.comboBoxLoaiNha.setSelectedIndex(1);
        } else if (loaiDat.toLowerCase().equals("thường")) {
            this.comboBoxLoaiNha.setSelectedIndex(0);
        } else {
            this.comboBoxLoaiNha.setSelectedIndex(-1);
        }

        this.textDiaChi.setText(diaChi);
        this.textDienTich.setText(dienTich);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String cmd = e.getActionCommand();
            Transaction transaction;

            try {
                transaction = this.getCurrentTransaction();
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }
            Command command = null;

            if (cmd.equals("Thêm")) {
                command = addTransaction(transaction);

            } else if (cmd.equals("Sửa")) {
                command = updateTransaction(transaction);

            } else if (cmd.equals("Xóa")) {
                command = deleteTransaction();

            } else if (cmd.equals("Tìm theo ID")) {
                command = findByID();

            } else if (cmd.equals("Thành tiền")) {
                command = calcMoney(transaction);
            
            } else if (cmd.equals("Tổng loại nhà")) {
                command = sumCountHouse();
            
            } else if (cmd.equals("Tổng loại đất")) {
                command = sumCountLand();

            } else if (cmd.equals("Trung bình TT nhà")) {
                command = avgMoneyHouse();

            } else if (cmd.equals("Trung bình TT đất")) {
                command = avgMoneyLand();
                
            } else if (cmd.equals("Xuất giao dịch")) {
                command = exportData();

            } else if (cmd.equals("Đóng")) {
                close();

            }

            if (command == null) {
                return;
            }

            commandProcessor.execute(command);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update() {
        Transaction transaction = this.transactionService.getTransaction();
        List<Transaction> transactions = this.transactionService.getTransactions();
        double thanhTien = this.transactionService.getThanhTien();
        int soLuong = this.transactionService.getCountTransaction();
        double avgMoney = this.transactionService.getAvgMoney();

        if (transaction != null) {
            setTransaction(transaction);
        } else if (transactions != null) {
            reloadTable(transactions);
        } else if (thanhTien > -1) {
            JOptionPane.showMessageDialog(this, "Thành tiền: " + thanhTien);
        } else if (soLuong > -1) {
            JOptionPane.showMessageDialog(this, "Số lượng: " + soLuong);
        } else if (avgMoney > -1) {
            JOptionPane.showMessageDialog(this, "Trung bình thành tiền: " + avgMoney);
        } else {
            clearInput();
            reloadTable();
        }
    }
}
