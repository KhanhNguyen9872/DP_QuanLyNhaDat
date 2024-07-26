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

import domain.model.House;
import observer.Subscriber;
import presentation.commandprocessor.*;
import presentation.commandprocessor.houseui.*;
import domain.HouseService;

public class HouseUI extends JFrame implements Subscriber, ActionListener, ListSelectionListener {
    private HouseService houseService = null;

    private JMenuBar jMenuBar = null;
    private DefaultTableModel tableModel = null;
    private JTable houseTable = null;
    private JButton addButton, updateButton, deleteButton, findIDButton, sumButton, sumCountButton, avgButton, exportButton;
    private JLabel labelMaGiaoDich, labelNgayGiaoDich, labelDonGia, labelLoaiNha, labelDiaChi, labelDienTich, labelTimKiem, labelMonth;
    private JTextField textMaGiaoDich, textDonGia, textDiaChi, textDienTich, textTimKiem;
    private JComboBox comboBoxLoaiNha, comboBoxMonth;
    private JXDatePicker JngayGiaoDich;
    private CommandProcessor commandProcessor;

    public HouseUI(HouseService houseService, CommandProcessor commandProcessor) {
        houseService.addSub(this);
        this.houseService = houseService;
        this.commandProcessor = commandProcessor;
        this.buildMenuBar();
        this.buildPanel();

        setSize(900, 480);
        setTitle("Quản lý nhà");
        setJMenuBar(this.jMenuBar);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reloadTable();
        clearInput();
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

        this.JngayGiaoDich = new JXDatePicker();
        this.JngayGiaoDich.setDate(Calendar.getInstance().getTime());
        this.JngayGiaoDich.setFormats(new SimpleDateFormat("yyyy/MM/dd"));

        this.textMaGiaoDich = new JTextField(10);
        this.textDonGia = new JTextField(10);
        this.textDiaChi = new JTextField(20);
        this.textDienTich = new JTextField(10);
        this.textTimKiem = new JTextField(20);
        
        String[] lst = {"Nhà thường", "Nhà cao cấp" };
        this.comboBoxLoaiNha = new JComboBox<>(lst);

        String[] lstMonth = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
        this.comboBoxMonth = new JComboBox<>(lstMonth);

        this.addButton = new JButton("Thêm");
        this.updateButton = new JButton("Sửa");
        this.deleteButton = new JButton("Xóa");
        this.findIDButton = new JButton("Tìm theo ID");
        this.sumButton = new JButton("Tính thành tiền");
        this.sumCountButton = new JButton("Tính tổng số lượng");
        this.avgButton = new JButton("Tính trung bình thành tiền");
        this.exportButton = new JButton("Xuất giao dịch");

        // Initialize table
        String[] columnNames = { "MaGiaoDich", "NgayGiaoDich", "DonGia", "LoaiNha", "DiaChi", "DienTich" };
        tableModel = new DefaultTableModel(columnNames, 0);
        houseTable = new JTable(tableModel);
        houseTable.getSelectionModel().addListSelectionListener(this);

        // add ActionListener
        this.addButton.addActionListener(this);
        this.updateButton.addActionListener(this);
        this.deleteButton.addActionListener(this);
        this.findIDButton.addActionListener(this);
        this.sumButton.addActionListener(this);
        this.sumCountButton.addActionListener(this);
        this.avgButton.addActionListener(this);
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
        inputPanel.add(this.JngayGiaoDich, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        inputPanel.add(this.labelDonGia, gbc);
        gbc.gridx++;
        inputPanel.add(this.textDonGia, gbc);
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
        buttonPanel.add(this.sumCountButton);
        buttonPanel.add(this.avgButton);
        buttonPanel.add(this.exportButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(this.houseTable), BorderLayout.CENTER);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        this.add(mainPanel);
    }

    public void reloadTable() {
        List<House> houses = this.houseService.getData();
        reloadTable(houses);
    }

    public void reloadTable(List<House> houses) {
        tableModel.setRowCount(0); // clear table

        for(House house : houses) {
            Object[] row = { house.getMaGiaoDich(), house.getNgayGiaoDich(), house.getDonGia(), house.getLoaiNha(), house.getDiaChi(), house.getDienTich() };
            tableModel.addRow(row);
        }
    }

    public void clearInput() {
        this.textMaGiaoDich.setText("");
        this.JngayGiaoDich.setDate(new Date("01/07/2024"));
        this.textDonGia.setText("");
        this.comboBoxLoaiNha.setSelectedIndex(0);
        this.textDiaChi.setText("");
        this.textDienTich.setText("");
    }

    public House getCurrentHouse() {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
        // Get data from input
        int maGiaoDich = Integer.parseInt(this.textMaGiaoDich.getText());
        String ngayGiaoDich = formater.format(this.JngayGiaoDich.getDate());
        int donGia = Integer.parseInt(this.textDonGia.getText());
        String loaiNha = this.comboBoxLoaiNha.getSelectedItem().toString();
        String diaChi = this.textDiaChi.getText();
        double dienTich = Double.parseDouble(this.textDienTich.getText());
        return new House(maGiaoDich, ngayGiaoDich, donGia, loaiNha, diaChi, dienTich);
    }

    public void addHouse() {
        try {
            House house = this.getCurrentHouse();

            // check house is exist or not
            if (this.houseService.findGetHouse(house.getMaGiaoDich()) != null) {
                // exist
                JOptionPane.showMessageDialog(this, "Mã giao dịch này đã tồn tại");
                return;
            };

            // add new house
            this.houseService.addHouse(house);

            // Clear input
            this.clearInput();
            reloadTable();
        } catch (NumberFormatException ex) {
            // can't parse to int
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ!");
        }
    }

    public Command calcMoney(House house) {
        if (this.houseService.findGetHouse(house.getMaGiaoDich()) == null) {
            JOptionPane.showMessageDialog(this, "Mã giao dịch này không tồn tại");
            return null;
        };
        return new CalcCommand(houseService, house);
    }

    public Command addHouse(House house) {
        if (this.houseService.findGetHouse(house.getMaGiaoDich()) != null) {
            JOptionPane.showMessageDialog(this, "Mã giao dịch này đã tồn tại");
            return null;
        };
        return new AddCommand(houseService, house);
    }

    public Command updateHouse(House house) {
        if (this.houseService.findGetHouse(house.getMaGiaoDich()) == null) {
            JOptionPane.showMessageDialog(this, "Mã giao dịch này không tồn tại");
            return null;
        };
        return new UpdateCommand(houseService, house);
    }

    public Command deleteHouse() {
        int index = this.houseTable.getSelectedRow();

        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dòng dữ liệu nào!");
            return null;
        }
        int maGiaoDich = Integer.parseInt(this.houseTable.getValueAt(index, 0).toString());
        return new DeleteCommand(houseService, maGiaoDich);
    }

    public Command sumCountHouse() {
        return new SumCountHouseCommand(this.houseService, this.comboBoxLoaiNha.getSelectedItem().toString());
    }

    public Command avgMoney() {
        return new AvgCommand(this.houseService);
    }

    public Command exportData() {
        return new ExportCommand(this.houseService, Integer.parseInt(this.comboBoxMonth.getSelectedItem().toString()));
    }

    public Command findByID() {
        String text = textTimKiem.getText();
        if (text == null || text.isEmpty()) {
            return null;
        }
        int maGiaoDich = Integer.parseInt(text);
        return new FindByIDCommand(houseService, maGiaoDich);
    }

    // public Command searchHouse() {
    //     String text = JOptionPane.showInputDialog(this, "Nhập mã giao dịch:");
    //     if (text == null || text.isEmpty()) {
    //         return null;
    //     }

    //     try {
    //         int maGiaoDich = Integer.parseInt(text);

    //         if (this.houseService.findGetHouse(maGiaoDich) == null) {
    //             JOptionPane.showMessageDialog(this, "Mã giao dịch này không tồn tại");
    //             return null;
    //         };

    //         return new FindByIDCommand(houseService, maGiaoDich);
    //     } catch (NumberFormatException ex) {
    //         JOptionPane.showMessageDialog(this, "Vui lòng nhập số");
    //     }
    //     return null;
    // }

    public void setHouse(House house) {
        String maGiaoDich = String.valueOf(house.getMaGiaoDich());
        String ngayGiaoDich = house.getNgayGiaoDich();
        String donGia = String.valueOf(house.getDonGia());
        String loaiNha = house.getLoaiNha();
        String diaChi = house.getDiaChi();
        String dienTich = String.valueOf(house.getDienTich());

        this.textMaGiaoDich.setText(maGiaoDich);
        this.JngayGiaoDich.setDate(new Date(ngayGiaoDich.replace('-', '/')));
        this.textDonGia.setText(donGia);
        if (loaiNha.toLowerCase().equals("nhà cao cấp")) {
            this.comboBoxLoaiNha.setSelectedIndex(1);
        } else {
            this.comboBoxLoaiNha.setSelectedIndex(0);
        }
        
        this.textDiaChi.setText(diaChi);
        this.textDienTich.setText(dienTich);
    }

    public void Close() {
        this.dispose();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int row = this.houseTable.getSelectedRow();

        if (row == -1) {
            return;
        }

        String maGiaoDich = this.houseTable.getModel().getValueAt(row, 0).toString();
        String ngayGiaoDich = this.houseTable.getModel().getValueAt(row, 1).toString();
        String donGia = this.houseTable.getModel().getValueAt(row, 2).toString();
        String loaiNha = this.houseTable.getModel().getValueAt(row, 3).toString();
        String diaChi = this.houseTable.getModel().getValueAt(row, 4).toString();
        String dienTich = this.houseTable.getModel().getValueAt(row, 5).toString();

        this.textMaGiaoDich.setText(maGiaoDich);
        this.JngayGiaoDich.setDate(new Date(ngayGiaoDich.replace('-', '/')));
        this.textDonGia.setText(donGia);
        if (loaiNha.toLowerCase().equals("nhà cao cấp")) {
            this.comboBoxLoaiNha.setSelectedIndex(1);
        } else {
            this.comboBoxLoaiNha.setSelectedIndex(0);
        }
        this.textDiaChi.setText(diaChi);
        this.textDienTich.setText(dienTich);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String cmd = e.getActionCommand();
            House house;

            try {
                house = this.getCurrentHouse();
            } catch (Exception ex) {
                house = null;
            }
            Command command = null;

            if (cmd.equals("Thêm")) {
                command = addHouse(house);

            } else if (cmd.equals("Sửa")) {
                command = updateHouse(house);

            } else if (cmd.equals("Xóa")) {
                command = deleteHouse();

            } else if (cmd.equals("Tìm theo ID")) {
                command = findByID();

            } else if (cmd.equals("Tìm")) {
                // command = searchHouse();

            } else if (cmd.equals("Tính thành tiền")) {
                command = calcMoney(house);
            
            } else if (cmd.equals("Tính tổng số lượng")) {
                command = sumCountHouse();
            
            } else if (cmd.equals("Tính trung bình thành tiền")) {
                command = avgMoney();
            
            } else if (cmd.equals("Xuất giao dịch")) {
                command = exportData();

            } else if (cmd.equals("Đóng")) {
                Close();

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
        House house = this.houseService.getHouse();
        List<House> houses = this.houseService.getHouses();
        double thanhTien = this.houseService.getThanhTien();
        int soLuong = this.houseService.getSoLuong();
        double avgMoney = this.houseService.getAvgMoney();

        if (house != null) {
            setHouse(house);
        } else if (houses != null) {
            reloadTable(houses);
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
