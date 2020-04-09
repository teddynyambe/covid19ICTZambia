import 'package:flutter/material.dart';

class TestWindow extends StatefulWidget {
  TestWindow({Key key}) : super(key: key);

  @override
  _TestWindowState createState() => _TestWindowState();
}

class _TestWindowState extends State<TestWindow> {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: RaisedButton(
        onPressed: () {
          print('Pressed');
        },
        child: const Text('Press me'),
      ),
    );
  }
}
