package com.company;

import com.company.model.Book;
import com.company.model.BorrowingHistory;
import com.company.model.BorrowingHistoryList;
import com.company.model.User;
import com.company.service.*;
import com.company.view.BookTableModel;
import com.company.view.BorrowingHistoryTableModel;
import com.company.view.LibrarianTableModel;
import com.company.view.UserTableModel;
import com.thoughtworks.xstream.XStream;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainProgram extends JFrame {

    private JPanel panel1;
    private JPanel rootPanel;
    private JTextField emailTextField;
    private JTextField phoneTextField;
    private JPanel addBookPanel;
    private JPanel addUserPanel;
    private JPanel userListPanel;
    private JPanel bookListPanel;
    private JTable userTable;
    private JPanel adminCardsPanel;
    private JPanel addUserFormPanel;
    private JTextField userNameTextField;
    private JLabel userNameLabel;
    private JLabel phoneLabel;
    private JButton addUserBtn;
    private JLabel addUserLabel;
    private JPanel book;
    private JButton deleteUserButton;
    private JTable booksTable;
    private JTextField bookIdTextField;
    private JTextField bookTitleTextField;
    private JTextField authorTextField;
    private JButton joinButton;
    private CardLayout cardLayout;
    private JButton showAddUserPanelButton;
    private JButton showAddBookPanelButton;
    private JPanel addBookFromPanel;
    private JTextField titleTextField;
    private JLabel titleLabel;
    private JLabel publisherLabel;
    private JLabel authorLabel;
    private JTextField publisherTextField;
    private JButton showBookListPanelButton;
    private JButton showUsersListPanelButton;
    private JTextField searchBookTextField;
    private JComboBox availabilityCbx;
    private JButton deleteUserBtn;
    private JButton updateUserBtn;
    private JButton showBorrowingHistoryPanelBtn;
    private JButton loadBooksFromCSVButton;
    private JTextField pagesTextField;
    private JLabel pagesLabel;
    private JLabel genreLabel;
    private JComboBox genreComboBox;
    private JButton addBookBtn;
    private JButton updateBookBtn;
    private JButton deleteBookButton;
    private JButton issueBookBtn;
    private JPanel borrowingHistoryPanel;
    private JTable borrowingHistoryTable;
    private JLabel borrowingHistoryLabel;
    private JButton exportBorrowingHistoryToCSV;
    private JButton updateBorrowingHistoryRecordBtn;
    private JButton deleteBorrowingHistoryRecordBtn;
    private JButton returnBookBtn;
    private JComboBox searchAttributeCbx;
    private JButton showTopPanelBtn;
    private JPanel topBookAndReaderPanel;
    private JLabel topBookLabel;
    private JList topBookList;
    private JLabel firstReaderLabel;
    private JLabel secondReaderLabel;
    private JLabel thirdReaderLabel;
    private JLabel firstBook;
    private JLabel secondBook;
    private JLabel thirdBook;
    private JPanel loginPanel;
    private JTextField loginUserNameTextField;
    private JPasswordField passwordTextField;
    private JPanel loginFormPanel;
    private JButton loginButton;
    private JLabel userNameLoginLabel;
    private JLabel passwordLabel;
    private JLabel titleLoginLabel;
    private JButton loginAsLibrarianButton;
    private JSplitPane adminSplitPane;
    private JSplitPane librarianSplitPane;
    private JLabel adminLoginLabel;
    private JButton logoutButton;
    private JPanel librarianCardsPanel;
    private JButton addLibrarianButton;
    private JButton librarianListButton;
    private JPanel adminButtonsPanel;
    private JPanel librarianButtonsPanel;
    private JTextField addLibrarianLoginTextField;
    private JPasswordField addLibrarianPasswordTextField;
    private JPanel addLibrarianForm;
    private JLabel addLibrarianPanelLabel;
    private JPanel addLibrarianPanel;
    private JButton addNewLibrarianButton;
    private JPanel librariansListPanel;
    private JPanel librariansTablePanel;
    private JList topReadersList;
    private JTable librariansTable;
    private JPanel updateLibrarianPanel;
    private JTextField updateLoginTextField;
    private JTextField updatePasswordTextField;
    private JButton deleteLibrarianButton;
    private JButton updateLibrarianButton;
    private JPanel updateLibrarianFormPanel;
    private JButton exportToXMLButton;
    private JButton loadBorrowingHistoryFromXMLBTN;
    private JButton exportBorrowingHistoryToHTML;

    private List<User> usersList;
    private List<Book> bookList;
    private List<Librarian> librariansList;

    private List<BorrowingHistory> borrowingHistoryList;

    public MainProgram() throws SQLException {
        cardLayout = (CardLayout) (adminCardsPanel.getLayout());
        System.out.println(adminCardsPanel);


        System.out.println(borrowingHistoryTable.toString());
        loadLibrariansTable();
        loadUserTable();
        loadAllBooks();
        loadBorrowingHistoryTable();
        displayLoginPanel();

        addUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = userNameTextField.getText();
                String phone = phoneTextField.getText();
                String email = emailTextField.getText();

                User user = new User(name, email, phone);

                try {
                    UserService.insertUser(user);
                    loadUserTable();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        updateUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = userTable.getSelectedRows()[0];
                int id = Integer.parseInt(userTable.getValueAt(selectedRow,0).toString());
                int numberOfBorrows = Integer.parseInt(userTable.getValueAt(selectedRow,4).toString());
                String selectedUserName = userTable.getValueAt(selectedRow,1).toString();
                String selectedUserEmail = userTable.getValueAt(selectedRow,2).toString();
                String selectedUserPhone = userTable.getValueAt(selectedRow,3).toString();



                User user = new User(id ,selectedUserName, selectedUserEmail, selectedUserPhone, numberOfBorrows);

                try {
                    if (UserService.isUserExsited(id)) {
                        UserService.updateUser(user);
                        loadUserTable();
                        setUpdateBtnAndDeleteUserBtnNotVisable();
                    } else {
                        System.out.println("there");
                        JOptionPane.showMessageDialog( rootPanel,"Cannot update user. User don't exist");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        deleteUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = userTable.getSelectedRows()[0];
                int selectedUserId = Integer.parseInt(userTable.getValueAt(selectedRow,0).toString());

                try {
                    UserService.deleteUser(selectedUserId);
                    JOptionPane.showMessageDialog(rootPanel,"Successfully removed");
                    loadUserTable();
                    setUpdateBtnAndDeleteUserBtnNotVisable();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                deleteUserBtn.setVisible(true);
                updateUserBtn.setVisible(true);
            }
        });
        addBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleTextField.getText();
                String publisher = publisherTextField.getText();
                String author = authorTextField.getText();
                String pages = pagesTextField.getText();
                String genre = genreComboBox.getEditor().getItem().toString();

                Book book = new Book(title, author, genre, pages, publisher);

                try {
                    BookService.insertBook(book);
                    loadAllBooks();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });


        showAddUserPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                librarianCardsPanel.removeAll();
                librarianCardsPanel.add(addUserPanel);
                librarianCardsPanel.repaint();
                librarianCardsPanel.revalidate();
            }
        });
        showAddBookPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                librarianCardsPanel.removeAll();
                librarianCardsPanel.add(addBookPanel);
                librarianCardsPanel.repaint();
                librarianCardsPanel.revalidate();
            }
        });
        showBookListPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                librarianCardsPanel.removeAll();
                try {
                    loadAllBooks();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                librarianCardsPanel.add(bookListPanel);
                librarianCardsPanel.repaint();
                librarianCardsPanel.revalidate();
            }
        });
        showUsersListPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                librarianCardsPanel.removeAll();
                librarianCardsPanel.add(userListPanel);
                librarianCardsPanel.repaint();
                librarianCardsPanel.revalidate();
            }
        });

        showTopPanelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                librarianCardsPanel.removeAll();
                librarianCardsPanel.add(topBookAndReaderPanel);
                librarianCardsPanel.repaint();
                librarianCardsPanel.revalidate();

                displayTopBook();
                displayTopReader();
            }
        });
        loadBooksFromCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser("/Users/mikolajtelus/Documents/Java/CRUD2/src/com/company");
                int userChoice = jfc.showOpenDialog(rootPanel);
                if (userChoice == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = jfc.getSelectedFile();
                    try {
                        readBooksFromCSV(selectedFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                if (userChoice == JFileChooser.CANCEL_OPTION)
                    System.out.println("No file selected");
            }
        });



        booksTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateBookBtn.setVisible(true);
                deleteBookButton.setVisible(true);
                int selectedRow = booksTable.getSelectedRows()[0];
                int id = Integer.parseInt(booksTable.getValueAt(selectedRow,0).toString());

                try {
                    if(BookService.isBookAvailable(id)) {
                        issueBookBtn.setVisible(true);
                    } else {
                        issueBookBtn.setVisible(false);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = booksTable.getSelectedRows()[0];
                int selectedBookId = Integer.parseInt(booksTable.getValueAt(selectedRow,0).toString());

                try {
                    BookService.deleteBook(selectedBookId);
                    JOptionPane.showMessageDialog(rootPanel,"Successfully removed");
                    loadAllBooks();
                    setUpdateBtnAndDeleteBookBtnNotVisable();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        updateBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Book selectedBook = getSelectedBook();

                Book book = new Book(selectedBook.getId(), selectedBook.getTitle(), selectedBook.getAuthor(),
                                    selectedBook.getGenre(), selectedBook.getPages(), selectedBook.getPublisher(),
                                    selectedBook.getAvailbility(), selectedBook.getNumOfBorrows());


                try {
                    if (BookService.isBookExsited(book.getId())) {
                        BookService.updateBook(book);
                        loadAllBooks();
                        setUpdateBtnAndDeleteBookBtnNotVisable();
                    } else {
                        System.out.println("there");
                        JOptionPane.showMessageDialog( rootPanel,"Cannot update book. Book don't exist");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        issueBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        showIssueBookOptionPane();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
        });
        showBorrowingHistoryPanelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                librarianCardsPanel.removeAll();
                librarianCardsPanel.add(borrowingHistoryPanel);
                librarianCardsPanel.repaint();
                librarianCardsPanel.revalidate();
            }
        });

        borrowingHistoryTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = borrowingHistoryTable.getSelectedRows()[0];
                String returnDate = borrowingHistoryTable.getValueAt(selectedRow,4).toString();

                System.out.println(returnDate);
                if (returnDate.equals("-")) {
                    returnBookBtn.setVisible(true);
                } else {
                    returnBookBtn.setVisible(false);
                }

                updateBorrowingHistoryRecordBtn.setVisible(true);
                deleteBorrowingHistoryRecordBtn.setVisible(true);
            }
        });

        returnBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = borrowingHistoryTable.getSelectedRows()[0];
                int id = Integer.parseInt(borrowingHistoryTable.getValueAt(selectedRow,0).toString());
                int user_id = Integer.parseInt(borrowingHistoryTable.getValueAt(selectedRow,1).toString());
                int book_id = Integer.parseInt(borrowingHistoryTable.getValueAt(selectedRow,2).toString());

                String borrowDate = borrowingHistoryTable.getValueAt(selectedRow,3).toString();
                String returnDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

                BorrowingHistory borrowingHistory = new BorrowingHistory(id, user_id, book_id, borrowDate, returnDate);


                try {
                    BorrowingHistoryService.updateBorrowingHistory(borrowingHistory);
                    Book returnedBook = BookService.findBookById(book_id);
                    returnedBook.setAvailbility(1);
                    BookService.setAvailbility(returnedBook);
                    loadBorrowingHistoryTable();
                    setAllBorrowingHistoryPanelButtonNotVisable();
                }catch (SQLException ex) {
                    System.out.println("Cannot return book " + ex);
                }
            }
        });


        searchBookTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String keyWord = searchBookTextField.getText();
                String searchAttribute = searchAttributeCbx.getSelectedItem().toString();

                try {

                    if(searchAttribute.equals("Title")) {
                        loadBooksFromList(BookService.searchBookByTitle(keyWord));
                    } else {
                        loadBooksFromList(BookService.searchBookByAuthor(keyWord));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        exportBorrowingHistoryToCSV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file save");
                int userSelection = fileChooser.showSaveDialog(rootPanel);
                if(userSelection == JFileChooser.APPROVE_OPTION){
                    File fileToSave = fileChooser.getSelectedFile();

                    try {
                        FileWriter fw = new FileWriter(fileToSave);
                        BufferedWriter bw = new BufferedWriter(fw);

                        for (int i = 0; i < borrowingHistoryTable.getRowCount(); i++) {
                            for (int j = 0; j < borrowingHistoryTable.getColumnCount(); j++) {
                                bw.write(borrowingHistoryTable.getValueAt(i, j).toString()+",");
                            }
                            bw.newLine();
                        }
                        JOptionPane.showMessageDialog(rootPanel, "SUCCESSFULLY LOADED","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
                        bw.close();
                        fw.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(rootPanel, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
                    }


                }


            }
        });
        availabilityCbx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String availability = availabilityCbx.getSelectedItem().toString();

                if (availability.equals("Available")) {
                    try {

                        System.out.println("Available selected");
                        loadBooksByAvailability(1);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else if (availability.equals("Not available")) {
                    System.out.println("Available not selected");
                    try {
                        loadBooksByAvailability(0);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        loadAllBooks();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }




            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = loginUserNameTextField.getText();
                String password = String.valueOf(passwordTextField.getPassword());

                if (user.isEmpty() && password.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPanel, "Login and password are required");
                } else {
                    validateAdminLogin(user, password);
                }
            }
        });
        loginAsLibrarianButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = loginUserNameTextField.getText();
                String password = String.valueOf(passwordTextField.getPassword());

                if (user.isEmpty() && password.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPanel, "Login and password are required");
                } else {
                    validateLibrarianLogin(user, password);
                }
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                displayLoginPanel();
            }
        });

        addLibrarianButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminCardsPanel.removeAll();
                adminCardsPanel.add(addLibrarianPanel);
                adminCardsPanel.repaint();
                adminCardsPanel.revalidate();
            }
        });
        addNewLibrarianButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = addLibrarianLoginTextField.getText();
                String password = String.valueOf(addLibrarianPasswordTextField.getPassword());

                if (login.isEmpty() && password.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPanel, "Login and password are required");
                } else {
                    Librarian librarian = new Librarian(login, password);
                    try {
                        LibrarianService.insertLibrarian(librarian);
                        JOptionPane.showMessageDialog(rootPanel, "Successfully added new Librarian!");
                        loadLibrariansTable();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        librarianListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminCardsPanel.removeAll();
                adminCardsPanel.add(librariansListPanel);
                adminCardsPanel.repaint();
                adminCardsPanel.revalidate();
            }
        });
        librariansTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateLibrarianButton.setVisible(true);
            }
        });


        updateLibrarianButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Librarian selectedLibrarian = getSelectedLibrarian();
                updateLoginTextField.setText(selectedLibrarian.getLogin());
                updatePasswordTextField.setText(selectedLibrarian.getPassword());

                int result = JOptionPane.showConfirmDialog(null, updateLibrarianFormPanel, "Update Librarian",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    try {

                        Librarian updatedLibrarian = new Librarian(selectedLibrarian.getId(), updateLoginTextField.getText(), updatePasswordTextField.getText());
                        LibrarianService.updateLibrarian(updatedLibrarian);
                        loadLibrariansTable();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                } else {
                    System.out.println("Cancelled");
                }
            }
        });
        exportToXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {

                XStream xstream = new XStream();
                xstream.alias("borrowingHistory", BorrowingHistory.class);
                xstream.alias("borrowingHistoryList", BorrowingHistoryList.class);
                xstream.addImplicitCollection(BorrowingHistoryList.class, "borrowingHistoryList");

                String xml = xstream.toXML(borrowingHistoryList);

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file save");
                int userSelection = fileChooser.showSaveDialog(rootPanel);
                if(userSelection == JFileChooser.APPROVE_OPTION){
                    File fileToSave = fileChooser.getSelectedFile();

                    try {
                        FileWriter fw = new FileWriter(fileToSave);
                        BufferedWriter bw = new BufferedWriter(fw);

                        bw.write(xml);
                        JOptionPane.showMessageDialog(rootPanel, "SUCCESSFULLY LOADED","INFORMATION",JOptionPane.INFORMATION_MESSAGE);
                        bw.close();
                        fw.close();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(rootPanel, "ERROR","ERROR MESSAGE",JOptionPane.ERROR_MESSAGE);
                    }


                }
            }
        });
        loadBorrowingHistoryFromXMLBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser jfc = new JFileChooser("/Users/mikolajtelus/Documents/Java/CRUD2/src/com/company");
                int userChoice = jfc.showOpenDialog(rootPanel);
                if (userChoice == JFileChooser.APPROVE_OPTION)
                {
                    File selectedFile = jfc.getSelectedFile();
                    try {
                        readBorrowingHistoryFromXML(selectedFile);
                    } catch (IOException | SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                if (userChoice == JFileChooser.CANCEL_OPTION)
                    System.out.println("No file selected");

            }
        });


        exportBorrowingHistoryToHTML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveBorrowingHistoryAsHTML();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public Book getSelectedBook() {
        int selectedRow = booksTable.getSelectedRows()[0];
        int id = Integer.parseInt(booksTable.getValueAt(selectedRow,0).toString());
        String selectedBookTitle = booksTable.getValueAt(selectedRow,1).toString();
        String selectedBookAuthor = booksTable.getValueAt(selectedRow,2).toString();
        String selectedBookGenre = booksTable.getValueAt(selectedRow,3).toString();
        String selectedBookPages = booksTable.getValueAt(selectedRow,5).toString();
        String publisher = booksTable.getValueAt(selectedRow,4).toString();
        int numOfBorrows= Integer.parseInt(booksTable.getValueAt(selectedRow,7).toString());
        int availbility = Integer.parseInt(booksTable.getValueAt(selectedRow,6).toString());

        Book selectedBook = new Book(id, selectedBookTitle, selectedBookAuthor,
                selectedBookGenre, selectedBookPages, publisher, availbility, numOfBorrows);

        return selectedBook;

    }

    public Librarian getSelectedLibrarian() {
        int selectedRow = librariansTable.getSelectedRows()[0];
        int id = Integer.parseInt(librariansTable.getValueAt(selectedRow,0).toString());
        String selectedLibrarianLogin = librariansTable.getValueAt(selectedRow,1).toString();
        String selectedLibrarianPassword = librariansTable.getValueAt(selectedRow,2).toString();

        Librarian selectedLibrarian = new Librarian(id, selectedLibrarianLogin, selectedLibrarianPassword);

        return selectedLibrarian;
    }

    public void showIssueBookOptionPane() throws SQLException {
        User[] usersArray = usersList.toArray(new User[0]);

        JPanel panel = new JPanel(new GridLayout(0, 1));

        panel.add(new JLabel("Users:"));
        JComboBox<User> userBox = new JComboBox<>(usersArray);
        panel.add(userBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Issue Book",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {

            Book selectedBook = getSelectedBook();
            User selectedUser = (User) userBox.getSelectedItem();

            int selectedUserId = selectedUser.getId();
            int selectedBookId = selectedBook.getId();

            String borrowDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
            String retrunDate = "-";
            selectedBook.setAvailbility(0);
            selectedBook.incrementNumOfBorrows();
            selectedUser.increaseNumberOfBorrows();

            BorrowingHistory borrowingHistoryRecord = new BorrowingHistory(selectedUserId, selectedBookId, borrowDate, retrunDate);

            try {
                BorrowingHistoryService.addRecordToBorrowingHistory(borrowingHistoryRecord);
                BookService.setAvailbility(selectedBook);
                BookService.setNumOfBorrows(selectedBook);
                UserService.setNumOfBorrows(selectedUser);
                loadAllBooks();
                loadBorrowingHistoryTable();
            } catch (SQLException e) {
                System.out.println("Cannot issue book");
                System.out.println(e.toString());
            }


        } else {
            System.out.println("Cancelled");
        }
    }



    public void readBooksFromCSV(File file) throws IOException {
        FileReader fr = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String title = values[0];
                String author = values[1];
                String genre = values[4];
                String pages = values[5];
                String publisher = values[4];

                Book book = new Book(title, author, genre, pages, publisher);
                BookService.insertBook(book);
                loadAllBooks();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveBorrowingHistoryAsHTML() throws IOException {
        FileWriter html = new FileWriter("table.html");
        TableModel model = borrowingHistoryTable.getModel();

        html.write("<html> <head><meta charset=\"utf-8\"></head><style>\n"
                + "th, td {"
                + "    font-size: 12pt;"
                + "}"
                + "</style><table border=\"1\"><tr>");

        for (int i = 0; i < borrowingHistoryTable.getColumnCount(); i++) {
            html.write("<th>"  + model.getColumnName(i) + "</th>");
        }

        html.write("</tr>");

        for (int i = 0; i < model.getRowCount(); i++) {
            html.write("<tr>");
            for (int j = 0; j < model.getColumnCount(); j++) {
                html.write("<td>" + model.getValueAt(i,j).toString() + "</td>");
            }
            html.write("</tr>");
        }

        html.write("</table></html>");
        html.close();



    }

    public void readBorrowingHistoryFromXML(File file) throws IOException, SQLException {
        System.out.println(file.toString());
        XStream xstream = new XStream();
        xstream.alias("borrowingHistory", BorrowingHistory.class);
        xstream.alias("borrowingHistoryList", BorrowingHistoryList.class);
        xstream.addImplicitCollection(BorrowingHistoryList.class, "borrowingHistoryList", BorrowingHistory.class);
        xstream.allowTypes(new Class[] {com.company.model.BorrowingHistory.class});

         List<BorrowingHistory> borrowingHistoryListFromXML = (List<BorrowingHistory>) xstream.fromXML(file);
         for (BorrowingHistory borrowingHistoryRecord : borrowingHistoryListFromXML) {
             BorrowingHistoryService.addRecordToBorrowingHistory(borrowingHistoryRecord);
         }
         loadBorrowingHistoryTable();
    }


    public void loadBooksFromList(List<Book> bookList) {
        BookTableModel bookTableModel = new BookTableModel(bookList);
        booksTable.setModel(bookTableModel);
    }
    public void loadAllBooks() throws SQLException {
        BookTableModel bookTableModel = new BookTableModel(BookService.loadAllBooks());
        booksTable.setModel(bookTableModel);
    }

    public void loadBooksByAvailability(int availbility) throws SQLException {
        BookTableModel bookTableModel = new BookTableModel(BookService.loadAllByAvailbility(availbility));
        booksTable.setModel(bookTableModel);
    }

    public void loadUserTable() throws SQLException {
        usersList = UserService.loadAllUsers();
        UserTableModel userTableModel = new UserTableModel(usersList);
        userTable.setModel(userTableModel);
    }

    public void loadBorrowingHistoryTable() throws SQLException {
        borrowingHistoryList = BorrowingHistoryService.loadBorrowingHistory();
        BorrowingHistoryTableModel borrowingHistoryTableModel
                = new BorrowingHistoryTableModel(borrowingHistoryList);
        borrowingHistoryTable.setModel(borrowingHistoryTableModel);
    }

    public void setUpdateBtnAndDeleteUserBtnNotVisable() {
        this.updateUserBtn.setVisible(false);
        this.deleteUserBtn.setVisible(false);
    }

    public void loadLibrariansTable() throws SQLException {
        librariansList = LibrarianService.loadAllLibrarians();
        LibrarianTableModel librarianTableModel = new LibrarianTableModel(librariansList);
        librariansTable.setModel(librarianTableModel);
    }

    public void setUpdateBtnAndDeleteBookBtnNotVisable() {
        this.deleteBookButton.setVisible(false);
        this.updateBookBtn.setVisible(false);
    }

    public void setAllBorrowingHistoryPanelButtonNotVisable() {
        updateBorrowingHistoryRecordBtn.setVisible(false);
        deleteBorrowingHistoryRecordBtn.setVisible(false);
        returnBookBtn.setVisible(false);
    }


    public void displayTopBook() {
        List<Book> mostReadedBook = null;
        DefaultListModel modelList = new DefaultListModel();

        try {
            mostReadedBook = BookService.getMostReadedBooks();

            firstBook.setText("1. " + mostReadedBook.get(0).getTitle());
            secondBook.setText("2. " + mostReadedBook.get(1).getTitle());
            thirdBook.setText("3. " + mostReadedBook.get(2).getTitle());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void validateAdminLogin(String login, String password) {

        try {
            if (AdminService.validateAdminLoginData(login, password)) {
                JOptionPane.showMessageDialog(rootPanel, "Logged Successful");
                adminLoginLabel.setText("Logged as " + login);
                loadAdminPanel();

            } else {
                JOptionPane.showMessageDialog(rootPanel, "Incorrect data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void validateLibrarianLogin(String login, String password) {

        try {
            if (LibrarianService.validateLibrarianLoginData(login, password)) {
                JOptionPane.showMessageDialog(rootPanel, "Logged Successful");
                loadLibrarianPanel();

            } else {
                JOptionPane.showMessageDialog(rootPanel, "Incorrect data");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void displayLoginPanel() {
        rootPanel.removeAll();
        rootPanel.add(loginPanel);
        rootPanel.repaint();
        rootPanel.revalidate();
    }

    public void loadLibrarianPanel() {
        rootPanel.removeAll();
        rootPanel.add(librarianSplitPane);
        rootPanel.repaint();
        rootPanel.revalidate();
    }

    public void loadAdminPanel() {
        rootPanel.removeAll();
        rootPanel.add(adminSplitPane);
        rootPanel.repaint();
        rootPanel.revalidate();
    }

    public void displayTopReader() {
        List<User> topReaders = null;
        DefaultListModel modelList = new DefaultListModel();

        try {
            topReaders = UserService.getTopReader();

            firstReaderLabel.setText("1. " + topReaders.get(0).getName());
            secondReaderLabel.setText("2. " + topReaders.get(1).getName());
            thirdReaderLabel.setText("3. " + topReaders.get(2).getName());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }




        public static void main (String[]args){

            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    MainProgram mp = null;
                    try {
                        mp = new MainProgram();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    mp.setContentPane(mp.rootPanel);
                    mp.pack();
                    mp.setLocationRelativeTo(null);
                    mp.setVisible(true);
                    mp.setSize(1000,700);

                }
            });


        }


}



