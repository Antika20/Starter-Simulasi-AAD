# Project Overview
These are the list of features that is available on the starter project for this app. You will need to have a good understanding of all these features to be able to do the simulation.
### List Screen 
![This is an image](https://github.com/Antika20/Starter-Simulasi-AAD/blob/master/List%20Screen.jpeg?raw=true)
* Option Menu
** Filter tasks by all, active, or completed.
** Navigate to the Settings screen.

* Floating Action Button (FAB) :
** android.R.drawable.ic_input_add

* List item :
** Checkbox (R.id.item_checkbox)
** TaskTitleView (R.id.item_tv_title)
***  TextAppearance : MaterialComponents.Headline6
*** MaxLines  :1

* TextView (R.id.item_tv_description)
** TextAppearance : MaterialComponents.Subtitle2

### Add Task Screen 
![This is an image](https://github.com/Antika20/Starter-Simulasi-AAD/blob/master/Add%20Task%20Screen.jpeg?raw=true)
* Option Menu
** Save Task 

* Input
** TextInputEditText (R.id.add_ed_title) -> MaxLines : 1
** TextInputEditText (R.id.add_ed_description) -> MaxLines : 5

* Image Button 
** onClick : showDatePicker, src : @drawabale/ic_date

### Detail Task Screen 
![This is an image](https://github.com/Antika20/Starter-Simulasi-AAD/blob/master/Detail%20Task%20Screen.jpeg?raw=true)
* Show data 
** TextInputEditText (R.id.detail_ed_title) -> MaxLines : 1, Focusable : false
** TextInputEditText (R.id.detail_ed_description) -> MaxLines : 5 , Focusable : false
** TextInputEditText (R.id.detail_ed_due_date) -> Focusable: False 
** Button (R.id.btn_delete_task) -> backgroundTint: @android:color/holo_red_light

* Up Navigation Button :
** Navigates to List Screen

### Settings Screen 
![This is an image](https://github.com/Antika20/Starter-Simulasi-AAD/blob/master/Settings%20Screen.jpeg?raw=true)
* Setting Preference:
** Reminder Notification -> Toggle setting to enable or disable daily reminder notification. 

* Up Navigation Button 
** Navigates to List Screen

### Notifications
![This is an image](https://github.com/Antika20/Starter-Simulasi-AAD/blob/master/Notification.jpeg?raw=true)
* Small icon : R.drawable.ic_notification
* Content title : Nearest Active Task Title
* Content text : R.string.notify_content with Nearest Active Task Due Date

## Task 
1. Define a local database table and DAO (data access object) based on schema in app/schemas/tasks.json. Use FilterUtils#geFilteredQuery to create a filterable query.
2.  Initiate RecyclerView with TaskAdapter and update database when onCheckChange.
3.  Display title in list-item based on state using TitleTextView (CustomView)
4.  Show a detailed task when the list is selected and implement a delete action.
5.  Create AddTaskViewModel class to insert a new "task" into the database.
6. Schedule and cancel daily reminder using WorkManager. If notification preference is on, get the earliest active task from the repository and show notification with pending intent.
7. Address the following comment from the QA team:
SnackBar does not show when the task completed/activated.
8. Write UI test to validate when user tap Add Task (+), the AddTaskActivity displayed.

## Exit Interview 
Tujuan wawancara ini adalah untuk melihat seberapa baik Anda memahami proyek ujian:

1. Proyek apa yang telah Anda kerjakan?
2. Bagian mana yang paling sulit?
3. Komponen apa yang telah digunakan untuk menampilkan daftar tugas?
4. Bagaimana cara kerja pengingat notifikasi?
5. Mengapa kita membutuhkan Tampilan Kustom?

## Tips 
Berikut adalah beberapa tips untuk menjawab setiap pertanyaan:

1. Ceritakan tentang apa tujuan dan fitur aplikasi. Anda juga dapat memberi tahu arsitektur proyek.
2. Bicara tentang tugas mana yang paling sulit. Jelaskan mengapa sulit dan bagaimana Anda mengatasi masalah itu.
3. Beri tahu komponen yang digunakan untuk menampilkan tugas, seperti RecyclerView, adaptor, pengelola tata letak, dan Paging. Anda juga dapat mengetahui dari mana data tersebut berasal.
4. Beri tahu alur kerja tentang cara mengaktifkan pengingat dan jelaskan secara teknis komponen apa yang digunakan, seperti SharedPreferences, WorkManager (WorkRequest, Worker), Notification Manager, Repository untuk mendapatkan data, dan Pending Intent. 
5. Jelaskan arti dari Tampilan Kustom. Tunjukkan Tampilan Kustom apa yang digunakan dalam aplikasi ini beserta fitur dan manfaatnya.
