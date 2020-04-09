import '../model/result.dart';
import './SymptomsScreen.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../widgets/ResultItem.dart';

class HomeScreen extends StatefulWidget {
  HomeScreen({Key key}) : super(key: key);

  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  var _isInit = true;
  var _isLoading = false;

  @override
  void didChangeDependencies() {
    if (_isInit) {
      _loadResults();
    }
    _isInit = false;

    super.didChangeDependencies();
  }

  Future<void> _loadResults() async {
    setState(() {
      _isLoading = true;
    });
    try {
      await Provider.of<ManageResult>(context, listen: false).setResults();
    } catch (error) {
      await showDialog(
        context: context,
        builder: (ctx) => AlertDialog(
          title: Text('Error occured'),
          content: Text('Try again later. \nError details:${error.toString()}'),
        ),
      );
    }
    setState(() {
      _isLoading = false;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: <Widget>[
          Container(
            height: 200,
            child: Stack(
              children: <Widget>[
                Image.asset(
                  'assets/images/falls.jpg',
                  width: double.infinity,
                  fit: BoxFit.cover,
                ),
                Align(
                    alignment: Alignment.bottomLeft,
                    child: Padding(
                        padding: EdgeInsets.all(10),
                        child: Text(
                          'COVID19 | Zambia',
                          style: TextStyle(
                              fontSize: 40,
                              fontWeight: FontWeight.bold,
                              color: Colors.white),
                        ))),
              ],
            ),
          ),
          SizedBox(
            height: 20,
          ),
          _isLoading
              ? CircularProgressIndicator()
              : Card(
                  margin: EdgeInsets.symmetric(horizontal: 10, vertical: 10),
                  child: Container(
                    padding: EdgeInsets.symmetric(vertical: 5, horizontal: 10),
                    child: Column(
                      children: <Widget>[
                        Align(
                            alignment: Alignment.topRight,
                            child: IconButton(
                                icon: Icon(Icons.refresh),
                                onPressed: () => _loadResults())),
                        Consumer<ManageResult>(
                          builder: (ctx, r, ch) => Column(
                            children: <Widget>[
                              Container(
                                padding: EdgeInsets.symmetric(
                                    horizontal: 10, vertical: 10),
                                child: Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceBetween,
                                  children: <Widget>[
                                    SizedBox(width: 20),
                                    Text(
                                      'Zambia',
                                      style: TextStyle(
                                          fontSize: 20,
                                          fontWeight: FontWeight.bold),
                                    ),
                                    Text(
                                      'World',
                                      style: TextStyle(
                                          fontSize: 20,
                                          fontWeight: FontWeight.bold),
                                    )
                                  ],
                                ),
                              ),
                              ResultItem(
                                color: Colors.black54,
                                title: 'Confirmed',
                                world: r.result.confirmedWorld,
                                zm: r.result.confirmedZambia,
                              ),
                              ResultItem(
                                color: Colors.red,
                                title: 'Deaths',
                                zm: r.result.deathsZambia,
                                world: r.result.deathsWorld,
                              ),
                              ResultItem(
                                color: Colors.green,
                                title: 'Recovered',
                                zm: r.result.recoveredZambia,
                                world: r.result.recoveredWorld,
                              ),
                            ],
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
          SizedBox(
            height: 10,
          ),
          SizedBox(
            width: double.infinity,
            child: Container(
              margin: EdgeInsets.all(10),
              child: RaisedButton(
                color: Colors.green,
                onPressed: () {
                  Navigator.of(context).pushNamed(SymptomsScreen.routeName);
                },
                child: Container(
                  margin: EdgeInsets.symmetric(vertical: 20),
                  child: Text(
                    'Submit my symptoms today',
                    style: TextStyle(fontSize: 20, color: Colors.white),
                  ),
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
