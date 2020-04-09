import 'package:flutter/material.dart';
import 'package:number_display/number_display.dart';

class ResultItem extends StatelessWidget {
  final title;
  final zm;
  final world;
  final color;
  const ResultItem({Key key, this.title, this.world, this.zm, this.color}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final display = createDisplay(separator: ',', length: 20);
    return Container(
      margin: EdgeInsets.symmetric(vertical: 10),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: <Widget>[
          Container(
            width: 120,
            child: Text(
              '$title:',
              style: TextStyle(fontSize: 20),
            ),
          ),
          Container(
            alignment: Alignment.centerRight,
            child: Text(
              display(zm),
              style: TextStyle(fontSize: 20, color: color),
      
            ),
          ),
          SizedBox(
            width: 150,
            child: Container(
              alignment: Alignment.centerRight,
              child: Text(
                display(world),
                style: TextStyle(fontSize: 20, color: color),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
