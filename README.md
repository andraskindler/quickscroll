About QuickScroll
=================

QuickScroll is a library aimed at creating similar scrolling experiences to the native Contacts app's People view. It has the same scrolling capabilities as the ListView's native *fastscroll*, but comes with a much a much more customizable design, two indicator styles and an allways visible scrollbar. The two most common modes are *popup* and *indicator*. Works seamlessly with ListView and ExpandableListView. Check out the demo application, available in the <a href="https://play.google.com/store/apps/details?id=com.andraskindler.quickscrollsample" target="blank_">Play Store</a>.

QuickScroll works great with basically any third party library, in fact, it's just a lightveight View subclass with few lines of initialization code. For more info check out this <a href="http://andraskindler.com/blog/2013/quickscroll-a-customizable-always-visible-scrollbar-for-listview-and-expandablelistview/" target="blank_">blog post</a>.

<p align="center"><img src="http://howrobotswork.files.wordpress.com/2013/08/quickscroll_11.png"/></p>

Usage
=====

*A project with mulitple working samples can be found in the /sample folder.*

1. Include the widget in your layout. Currently, you can only put it to the right side of the ListView, this can be done via a RelativeLayout, a LinearLayout or any other positioning trick of your choice. The default width is 30 dp, but if you really want to change it, you can do it via an object method.

        <com.andraskindler.quickscroll.QuickScroll
        android:id="@+id/quickscroll"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"/>

2. Implement the *Scrollable* interface in your BaseExpandableListAdapter or BaseAdapter subclass. You must override the methods below.
 
   The first function returns the corresponding String to display at any given position:
        
        @Override
        public String getIndicatorForPosition(int childposition, int groupposition) {
            return null;
        }

   The second function is responsible for is for implementing scroll behaviour. This can be used to perform special tasks, e.g. if you want to *snap* to the first item starting with a letter in an alphabetically ordered list or jump between groups in an ExpandableListView. If you want the normal approach, simply return *childposition*.

        @Override
        public int getScrollPosition(int childposition, int groupposition) {
	    return 0;
        }
        
3. Initialize the QuickScroll widget:
	
        final QuickScroll quickscroll = (QuickScroll) layout.findViewById(R.id.quickscroll);
        quickscroll.init(QuickScroll.TYPE_INDICATOR, listview, adapter, QuickScroll.STYLE_HOLO);

	The first parameter assigns the type, the second and third binds the ListView (or ExpandableListView) and its adapter. 
	The last one defines the style, currently there are two available:
	* STYLE_HOLO creates a scrollbar fitting into stock holo design.
	* STYLE_NONE creates a transparent scrollbar, so you can create a fully customised underlying layout.

	Both styles also come with a *_WITH_HANDLE* suffix, creating a holo-themed handlebar on the scrollbar.

4. (Optional) Do some customizing: change the colors, sizes and font styles. This can be done via object methods.

Including in your app
=====================

QuickScroll is available as an Android Library Project. In Eclipse, you can include it by referencing as a library project, in IntelliJ, you can add it as a module.

<p align="center"><img src="http://howrobotswork.files.wordpress.com/2013/08/quickscroll_21.png"/></p>

Other notices
=============

The lowest supported API level is 8 (FroYo)

Developed by **Andras Kindler** (andraskindler@gmail.com)

License
=======

    Copyright 2013 Andras Kindler

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

