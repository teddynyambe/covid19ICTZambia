import 'package:covid19_app/model/symptoms.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class SymptomsScreen extends StatefulWidget {
  static const routeName = '/symptoms';
  SymptomsScreen({Key key}) : super(key: key);

  @override
  _SymptomsScreenState createState() => _SymptomsScreenState();
}

class _SymptomsScreenState extends State<SymptomsScreen> {
  var _fever = false;
  var _soreThroat = false;
  var _muscleJointPain = false;
  var _fatigue = false;
  var _dryCough = false;
  var _headache = false;
  var _shortBreath = false;
  var _runnyNose = false;

  var _saving = false;

  Symptom symptom;

  Future<void> _save() async {
    symptom = Symptom(
        dryCough: _dryCough,
        fatigue: _fatigue,
        fever: _fever,
        headache: _headache,
        muscleJointPain: _muscleJointPain,
        runnyNose: _runnyNose,
        shortBreath: _shortBreath,
        soreThroat: _soreThroat);

    try {
      setState(() {
        _saving = true;
      });
      await Provider.of<Symptoms>(context, listen: false)
          .reportSymptoms(symptom);
      setState(() {
        _saving = false;
      });
    } catch (error) {
      await showDialog(
        context: context,
        builder: (ctx) => AlertDialog(
          title: Text('Error occured'),
          content: Text('Try again later. \nError details:${error.toString()}'),
        ),
      );
    }
    Navigator.of(context).pop();
  }

  @override
  Widget build(BuildContext context) {
    var _screenSize = MediaQuery.of(context).size;

    return Scaffold(
      appBar: AppBar(
        title: Text('My symptoms today',),
        backgroundColor: Color.fromRGBO(242, 157, 20, 100),
      ),
      body: Container(
        width: double.infinity,
        child: Form(
          child: Column(
            children: <Widget>[
              Row(
                children: <Widget>[
                  Container(
                    width: _screenSize.width / 2.0,
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.start,
                      children: <Widget>[
                        GestureDetector(
                            onTap: () {
                              print("tapped $_fever");
                              setState(() {
                                _fever = !_fever;
                              });
                            },
                            child: Row(
                              children: <Widget>[
                                Checkbox(
                                    value: _fever,
                                    onChanged: (value) {
                                      setState(() {
                                        _fever = !_fever;
                                      });
                                    }),
                                Text('Fever')
                              ],
                            )),
                        GestureDetector(
                          onTap: () {
                            setState(() {
                              _soreThroat = !_soreThroat;
                            });
                          },
                          child: Row(
                            children: <Widget>[
                              Checkbox(
                                  value: _soreThroat,
                                  onChanged: (value) {
                                    setState(() {
                                      _soreThroat = !_soreThroat;
                                    });
                                  }),
                              Text('Sore throat')
                            ],
                          ),
                        ),
                        GestureDetector(
                          onTap: () {
                            setState(() {
                              _muscleJointPain = !_muscleJointPain;
                            });
                          },
                          child: Row(
                            children: <Widget>[
                              Checkbox(
                                  value: _muscleJointPain,
                                  onChanged: (value) {
                                    setState(() {
                                      _muscleJointPain = !_muscleJointPain;
                                    });
                                  }),
                              SizedBox(
                                  width: 120,
                                  child: Text(
                                    'Muscle and joint pain',
                                    softWrap: true,
                                  ))
                            ],
                          ),
                        ),
                        GestureDetector(
                          onTap: () {
                            setState(() {
                              _fatigue = !_fatigue;
                            });
                          },
                          child: Row(
                            children: <Widget>[
                              Checkbox(
                                  value: _fatigue,
                                  onChanged: (value) {
                                    setState(() {
                                      _fatigue = !_fatigue;
                                    });
                                  }),
                              Text('Fatigue')
                            ],
                          ),
                        ),
                      ],
                    ),
                  ),
                  Container(
                    width: _screenSize.width / 2.0,
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.start,
                      children: <Widget>[
                        GestureDetector(
                          onTap: () {
                            setState(() {
                              _dryCough = !_dryCough;
                            });
                          },
                          child: Row(
                            children: <Widget>[
                              Checkbox(
                                  value: _dryCough,
                                  onChanged: (value) {
                                    setState(() {
                                      _dryCough = !_dryCough;
                                    });
                                  }),
                              Text('Dry cough')
                            ],
                          ),
                        ),
                        GestureDetector(
                          onTap: () {
                            setState(() {
                              _headache = !_headache;
                            });
                          },
                          child: Row(
                            children: <Widget>[
                              Checkbox(
                                  value: _headache,
                                  onChanged: (value) {
                                    setState(() {
                                      _dryCough = !_dryCough;
                                    });
                                  }),
                              Text('Headache')
                            ],
                          ),
                        ),
                        GestureDetector(
                          onTap: () {
                            setState(() {
                              _shortBreath = !_shortBreath;
                            });
                          },
                          child: Row(
                            children: <Widget>[
                              Checkbox(
                                  value: _shortBreath,
                                  onChanged: (value) {
                                    setState(() {
                                      _shortBreath = !_shortBreath;
                                    });
                                  }),
                              SizedBox(width:120, child: Text('Shortness of breath'))
                            ],
                          ),
                        ),
                        GestureDetector(
                          onTap: () {
                            setState(() {
                              _runnyNose = !_runnyNose;
                            });
                          },
                          child: Row(
                            children: <Widget>[
                              Checkbox(
                                  value: _runnyNose,
                                  onChanged: (value) {
                                    setState(() {
                                      _runnyNose = !_runnyNose;
                                    });
                                  }),
                              Text('Runny nose')
                            ],
                          ),
                        ),
                        SizedBox(
                          height: 20,
                        ),
                      ],
                    ),
                  )
                ],
              ),
              Container(
                width: double.infinity,
                margin: EdgeInsets.symmetric(
                  horizontal: 15,
                ),
                child: RaisedButton(
                  onPressed: () {
                    _save();
                  },
                  child: Padding(
                    padding: EdgeInsets.symmetric(vertical: 20),
                    child: _saving
                        ? CircularProgressIndicator()
                        : Text(
                            'Inform Ministry of Health',
                            style: TextStyle(fontSize: 20, color: Colors.white),
                          ),
                  ),
                  color: Colors.green,
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
