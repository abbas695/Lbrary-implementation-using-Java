package library2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class Library2 {
	static Scanner input = new Scanner(System.in);
	static book[] booklist = new book[50];
	static HashMap<String, book[]> bowrrowedbooks = new HashMap<String, book[]>();

	public static void main(String[] args) {
		int choice = 0;
		do {
			display();
			choice = input.nextInt();
			switch (choice) {
			case 1:
				System.out.println("enter the new book");
				book b = new book();
				newbook(b);
				break;
			case 2:
				System.out.println("enter the name of book");
				System.out.println(findquantity());
				break;
			case 3:
				allbooks();
				break;
			case 4:
				allstudents();
				break;
			case 5:
				displayboworredbooks();
				break;
			case 6:
				newstudent();
				break;
			case 7:
				bowrrowbook();
				break;
			case 8:
				returnbook();
				break;
			case 9:
				removebook();
				break;
			case 10:
				removestudent();
				break;
			case 11:
				input.nextLine();
				System.out.println("enter the name of book");
				String titleofbook = input.nextLine();
				findByTitle(titleofbook);
				System.out.println("date\t\tName\t\tAuthor\t\tAvailable Qty");
				System.out.println(findByTitle(titleofbook).date + "\t\t" + findByTitle(titleofbook).bookName + "\t\t"
						+ findByTitle(titleofbook).bookauthor + "\t\t" + findByTitle(titleofbook).bookQty);
				break;
			case 12:
				input.nextLine();
				System.out.println("enter the author of book");
				String authorofbook = input.nextLine();
				findByauthor(authorofbook);
				break;
			}
		} while (choice != -1);
	}

	public static void display() {
		System.out.println("Welcome Mr Admin");
		System.out.println("which one of the following operations would you like to do?");
		System.out.println("please");
		System.out.println("press 1 for adding a new book to the library");
		System.out.println("press 2 to find the quantatiy of a book in the library");
		System.out.println("press 3 to disblay all the books in the library");
		System.out.println("press 4 to display all of the regiestred students");
		System.out.println("press 5 to display the borrowed books by a regiestred student ");
		System.out.println("press 6 to add a new student");
		System.out.println("press 7 to bowrrow a book for a student");
		System.out.println("press 8 to return a book from a student");
		System.out.println("press 9 to remove a book from the library ");
		System.out.println("press 10 to remove a student ");
		System.out.println("press 11 to find a book by it's title ");
		System.out.println("press 12 to find all the books written by a certain author ");
		System.out.println("press -1 to exit");
	}

	public static book findByTitle(String title) {
		int count = booklist.length;
		for (int i = 0; i < count; i++) {
			if (booklist[i] == null) {
				continue;
			}
			if (booklist[i].bookName.equalsIgnoreCase(title)) {
				return booklist[i];
			}
		}
		return null;
	}

	public static void findByauthor(String author) {
		int count = booklist.length;
		for (int i = 0; i < count; i++) {
			if (booklist[i] == null) {
				continue;
			}
			if (booklist[i].bookauthor.equalsIgnoreCase(author)) {
				System.out.println("date\t\tName\t\tAuthor\t\tAvailable Qty");
				System.out.println(booklist[i].date + "\t\t" + booklist[i].bookName + "\t\t" + booklist[i].bookauthor
						+ booklist[i].bookQty);
			}
		}
	}

	public static void allstudents() {
		for (String i : bowrrowedbooks.keySet()) {
			System.out.println(i);
		}
	}

	public static void newstudent() {
		input.nextLine();
		System.out.println("enter the name of the new student");
		String studentinitial = input.nextLine();
		String student = studentinitial.toLowerCase();
		book[] studentbooks = new book[50];
		bowrrowedbooks.put(student, studentbooks);
	}

	public static int findquantity() {
		input.nextLine();
		String title = input.nextLine();
		int count = booklist.length;
		for (int i = 0; i < count; i++) {
			if (booklist[i] == null) {
				continue;
			}
			if (booklist[i].bookName.equalsIgnoreCase(title)) {
				return booklist[i].bookQty;
			}
		}
		return 0;
	}

	public static void allbooks() {
		int count = booklist.length;
		for (int i = 0; i < count; i++) {
			if (booklist[i] == null) {
				continue;
			}
			System.out.println("date\t\tName\t\tAuthor\t\tTotal Qty");
			System.out.println(booklist[i].date + "\t\t" + booklist[i].bookName + "\t\t" + booklist[i].bookauthor
					+ "\t\t" + booklist[i].bookQty);
		}
	}

	public static void removebook() {
		input.nextLine();
		System.out.println("enter the name of the book you want to remove");
		String removedbook = input.nextLine().toLowerCase();
		findByTitle(removedbook);
		if (findByTitle(removedbook) == null) {
			System.out.println("this book is not in the library");
		} else {
			System.out.println("enter the quantity you want to remove");
			int quantityremoved = input.nextInt();
			if (quantityremoved > findByTitle(removedbook).bookQty) {
				System.out.println("the quantity you entered is greater than the quatity presented");
			} else {
				findByTitle(removedbook).bookQty -= quantityremoved;
				if (findByTitle(removedbook).bookQty == 0) {
					int count = booklist.length;
					for (int i = 0; i < count; i++) {
						if (booklist[i] == null) {
							continue;
						} else {
							if (booklist[i] == findByTitle(removedbook)) {
								booklist[i] = null;
							}
						}
					}
				}
			}
		}
	}

	public static void newbook(book b) {
		input.nextLine();
		System.out.println("Enter date of Book:");
		b.date = input.nextLine();
		System.out.println("Enter Book Name:");
		b.bookName = input.nextLine();
		System.out.println("Enter Author Name:");
		b.bookauthor = input.nextLine();
		System.out.println("Enter Quantity of Books:");
		b.bookQty = input.nextInt();
		if (findByTitle(b.bookName) != null) {
			findByTitle(b.bookName).bookQty += b.bookQty;
		} else {
			int count = booklist.length;
			for (int i = 0; i < count; i++) {
				if (booklist[i] == null) {
					booklist[i] = b;
					System.out.println("date\t\tName\t\tAuthor\t\tAvailable Qty");
					System.out.println(booklist[i].date + "\t\t" + booklist[i].bookName + "\t\t"
							+ booklist[i].bookauthor + "\t\t" + booklist[i].bookQty);
					break;
				}
			}
		}
	}

	public static void removestudent() {
		input.nextLine();
		System.out.println("enter the name of the student you want to remove");
		String studentremovedinitial = input.nextLine();
		String studentremoved = studentremovedinitial.toLowerCase();
		for (String i : bowrrowedbooks.keySet()) {
			if (studentremoved.equalsIgnoreCase(i)) {
				int count = bowrrowedbooks.get(studentremoved).length;
				for (int j = 0; j < count; j++) {
					if (findByTitle(bowrrowedbooks.get(studentremoved)[j].bookName) != null) {
						findByTitle(bowrrowedbooks.get(studentremoved)[j].bookName).bookQty++;
						break;
					} else {
						int count2 = booklist.length;
						for (int k = 0; k < count2; k++) {
							if (booklist[k] == null) {
								booklist[k] = bowrrowedbooks.get(studentremoved)[j];
								System.out.println("date\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");
								System.out.println(booklist[k].date + "\t\t" + booklist[k].bookName + "\t\t"
										+ booklist[k].bookauthor + "\t\t" + booklist[k].bookQty);
								break;
							}
						}
					}
				}
				bowrrowedbooks.remove(i);
			} else {
				System.out.println("student is not regiestred");
			}
		}
	}

	public static void displayboworredbooks() {
		input.nextLine();
		boolean test = true;
		System.out.println("enter the name of the student you want to find his bowrrowed books");
		String initialboworredbooksofstudent = input.nextLine();
		String boworredbooksofstudent = initialboworredbooksofstudent.toLowerCase();
		int count = bowrrowedbooks.get(boworredbooksofstudent).length;
		for (int i = 0; i < count; i++) {
			if (bowrrowedbooks.get(boworredbooksofstudent)[i] == null) {
				continue;
			} else {
				test = false;
			}
			System.out.println("Name\t\tAuthor");
			System.out.println(bowrrowedbooks.get(boworredbooksofstudent)[i].bookName + "\t\t"
					+ bowrrowedbooks.get(boworredbooksofstudent)[i].bookauthor);
		}
		if (test) {
			System.out.println("the student does not have any bowrrowed books");
		}
	}

	public static void bowrrowbook() {
		System.out.println("enter the number of the books required to bowrrow");
		input.nextLine();
		System.out.println("enter the name of the  student");
		String studentbowrrowinitiial = input.nextLine();
		String studentbowrrow = studentbowrrowinitiial.toLowerCase();
		bowrrowedbooks.get(studentbowrrow);
		boolean condition = false;
		int choice = 1;
		int count = booklist.length;
		for (int i = 0; i < count; i++) {
			if (booklist[i] == null) {
				condition = true;
			} else {
				condition = false;
				break;
			}
		}
		if (condition) {
			System.out.println("the library is out of books");
		} else {
			do {
				System.out.println("enter the name of the book he wants to bowrrow");
				String bookname = input.nextLine();
				if (findByTitle(bookname) != null) {
					int count2 = bowrrowedbooks.get(studentbowrrow).length;
					for (int m = 0; m < count2; m++) {
						if (bowrrowedbooks.get(studentbowrrow)[m] == null) {
							bowrrowedbooks.get(studentbowrrow)[m] = findByTitle(bookname);
							break;
						}
					}
					if (findByTitle(bookname).bookQty > 1) {
						findByTitle(bookname).bookQty -= 1;
					} else {
						int count3 = booklist.length;
						for (int t = 0; t < count3; t++) {
							if (booklist[t] == null) {
								continue;
							} else {
								if (booklist[t] == findByTitle(bookname)) {
									booklist[t] = null;
								}
							}
						}
					}
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime now = LocalDateTime.now();
					System.out.println("date of borrowing is " + dtf.format(now));
					String thedatenow = now.toString();
					String days = thedatenow.substring(8, 10);
					int days2 = Integer.parseInt(days);
					String returningtime;
					days2 = days2 + 14;
					if (days2 > 31) {
						int days3 = days2 - 31;
						String month = thedatenow.substring(5, 7);
						int monthint = Integer.parseInt(month);
						int month2 = monthint + 1;
						String lsa = Integer.toString(days3);
						String psa = Integer.toString(month2);
						returningtime = thedatenow.substring(0, 5) + psa + "-" + lsa + " "
								+ thedatenow.substring(11, 19);
					} else {
						String hsa = Integer.toString(days2);
						returningtime = thedatenow.substring(0, 8) + hsa + " " + thedatenow.substring(11, 19);
					}
					System.out.println("last date to return the book is " + returningtime);
				} else {
					System.out.println("book is not in the library");
				}
				System.out.println("enter 1 to bowrrow another book for this student or 0 to return to main menu");
				choice = input.nextInt();
				input.nextLine();
			} while (choice != 0);
			allbooks();
		}
	}

	public static void returnbook() {
		input.nextLine();
		System.out.println("enter the name of the  student");
		String studentbowrrowinitial = input.nextLine();
		String studentbowrrow = studentbowrrowinitial.toLowerCase();
		bowrrowedbooks.get(studentbowrrow);
		int choice = 1;
		do {
			System.out.println("enter the name of the book he wants to return");
			String bookname = input.nextLine();
			int count = bowrrowedbooks.get(studentbowrrow).length;
			if (findByTitle(bookname) != null) {
				findByTitle(bookname).bookQty++;
				for (int i = 0; i < count; i++) {
					if (findByTitle(bookname) == bowrrowedbooks.get(studentbowrrow)[i]) {
						bowrrowedbooks.get(studentbowrrow)[i] = null;
						break;
					}
				}
			} else {
				int count2 = booklist.length;
				for (int i = 0; i < count; i++) {
					if (bowrrowedbooks.get(studentbowrrow)[i] == null) {
						continue;
					} else {
						if (bowrrowedbooks.get(studentbowrrow)[i].bookName.equalsIgnoreCase(bookname)) {
							for (int k = 0; k < count2; k++) {
								if (booklist[k] == null) {
									booklist[k] = bowrrowedbooks.get(studentbowrrow)[i];
									System.out.println("date\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");
									System.out.println(booklist[k].date + "\t\t" + booklist[k].bookName + "\t\t"
											+ booklist[k].bookauthor + "\t\t" + booklist[k].bookQty);
									bowrrowedbooks.get(studentbowrrow)[i] = null;
									break;
								}
							}
						}
					}
				}

			}
			System.out.println("enter 1 to return another book from this student or 0 to return to main menu");
			choice = input.nextInt();
			input.nextLine();
		} while (choice != 0);
	}
}
