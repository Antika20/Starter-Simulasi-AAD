# Project Overview 
These are the list of features that is available on the starter project for this app. You will need to have a good understanding of all these features to be able to do the simulation.

### Home Screen 
![This is an image](https://github.com/Antika20/Starter-Simulasi-AAD/blob/SUB-2/Home%20screen.jpeg?raw=true)
* Option Menu 
** Add Course (R.id.action_add), Show List (R.id.action_list), Settings (R.id.action_settings)

* CardHomeView 
** TextView (R.id.tv_course_home)-> TextColor : @color/textPrimary, TextSize: 18sp
** TextView (R.id.tv_time_home) -> TextColor : @color/textPrimary, TextSize: 16sp
** TextView (R.id.tv_remaining_time) -> TextStyle : italic
** TextView (R.id.tv_lecturer_home) -> layout_marginTop: 10dp
** TextView (R.id.tv_note_home) -> Ellipsize : end, MaxLines : 3, TextAlignment: center

### List Screen 
![This is an image](https://github.com/Antika20/Starter-Simulasi-AAD/blob/SUB-2/list%20screen%20sub%202.jpeg?raw=true)
* Option Menu 
** Sort Course (sort_course.xml) -> Sort by Time (R.id.sort_time), Sort by Course Name (R.id.sort_course_name), Sort by Lecturer (R.id.sort_lecturer), Settings (R.id.action_settings)
* Floating Action Button (FAB) : android.R.drawable.ic_input_add

* List item (item_course.xml):
*** TextView (R.id.tv_course) -> TextColor : @color/textPrimary, TextSize: 16sp, layout_marginBottom : 6dp
*** TextView (R.id.tv_time)
*** TextView (R.id.tv_lecturer)

### Add Course Screen
*** Option Menu -> Insert Course (R.id.action_insert)
***  Input -> 
**** TextInputEditText (R.id.ed_course_name), MaxLines : 1, MaxLength : 255
*** Spinner (R.id.spinner_day) -> MarginStart : 16dp, MarginTop : 16dp , MarginEnd : 16dp, Entries : @array/day, PaddingTop : 12 dp, PaddingBottom : 12 dp  
*** ImageButton (R.id.ib_start_time) ->  MarginStart : 16dp, MarginEnd : 16dp, src : @drawable/ic_time
*** TextView (R.id.tv_start_time) -> TextColor: @color/textPrimary
*** ImageButton (R.id.ib_end_time) -> MarginStart : 16dp, MarginEnd : 16dp, src : @drawabale/ic_time
*** TextView (R.id.tv_end_time) -> TextColor: @color/textPrimary
*** TextInputEditText (R.id.ed_lecturer) ->MaxLines : 1, MaxLength : 255
*** TextInputEditText (R.id.ed_note) -> Gravity : top
, ines: 4

# Detail Course Screen
![This is an image](https://github.com/Antika20/Starter-Simulasi-AAD/blob/SUB-2/Detail%20Course%20Screen.jpeg?raw=true)
* Option Menu: -> Delete Course (R.id.action_delete)
* show data 
** TextView -> Layout_marginTop : 16dp, DrawablePadding : 10dp, Text  @string/course_name, DrawableStartCompat : @drawable/ic_book
** TextView (R.id.tv_course_name) ->textColor : @color/textPrimary, textSize : 16sp
** TextView -> Layout_marginTop : 16dp, DrawablePadding : 10dp, Text : @string/time
DrawableStartCompat : @drawable/ic_time
** TextView (R.id.tv_lecturer) -> textColor : @color/textPrimary, textSize : 16sp
** TextView -> Layout_marginTop : 16dp, DrawablePadding : 10dp, Text : @string/note
DrawableStartCompat : @drawable/ic_note
** TextView (R.id.tv_note -> textColor : @color/textPrimary, textSize : 16sp

* Up Navigation Button 
** Navigates to List Screen

# Settings Screen 
![This is an image](https://github.com/Antika20/Starter-Simulasi-AAD/blob/SUB-2/Settings%20Screen%20sub%202.1.jpeg?raw=true)
![This is an image](https://github.com/Antika20/Starter-Simulasi-AAD/blob/SUB-2/Settings%20Screen%20sub%202.2.jpeg?raw=true)

* Setting Preference:
** Reminder Notification -> Toggle setting to enable or disable daily reminder notification. 
** Display Configuration -> Setting to change theme (automatic, dark mode, light mode)

* Up Navigation Button 
** Navigates to List Screen

## Notifications (Inbox Style)
![This is an image](https://github.com/Antika20/Starter-Simulasi-AAD/blob/SUB-2/Notification%20(Inbox%20Style).jpeg?raw=true)
* Small icon : R.drawable.ic_notifications
* Content title : R.string.today_schedule
* Content text : R.string.notification_message_format
 Kembali

## Task 
1. Define a local database table and DAO (data access object) based on schema in app/schemas/course.json. Use QueryUtils to create a raw query.
2. Display nearest schedule in CardHomeView (CustomView).
3. Initiate RecyclerView with CourseAdapter and delete the course when the list is swiped.
4. Create AddCourseActivity in ui/add to set a new course schedule (See: Add Course Screen specifications).
5. Update dark mode theme based on the value in ListPreference.
6. Schedule and cancel daily reminder for every 06.00 a.m using AlarmManager. Show today schedules in inbox style notification & open HomeActivity when notification tapped.
7. Address the following comment from the QA team:
- Sort menu does not show
- Course not deleted when the list is swiped
8. Write a UI test to validate that when users tap Add Course(+), the AddCourseActivity displayed.
 
# Exit Interview 
1. What project have you worked about?
2. Which part is hardest?
3. How is the flow to change the theme to a dark theme?
4. How does notification reminder work?
5. Why do we need LiveData?

# Tips 
These are some tips for answering each question:

1. Tell about what is the goal and features of the app. You also can tell the architecture of the project.
2. Talk about which one of the tasks is the hardest. Describe why it is hard and how you overcome that problem.
3. Tell the workflow of how to change the dark theme in Setting and what happens under the hood such as using AppCompateDelegate and recreate Activity.
4. Tell the workflow of how to enable the reminder and explain technically what components are used, such as SharedPreferences, AlarmManager, BroadcastReceiver, Notification Manager, Repository to get the data, and Pending Intent. 
5. Explain the meaning of LiveData. Show how LiveData is used in this app along with the features and benefits.
