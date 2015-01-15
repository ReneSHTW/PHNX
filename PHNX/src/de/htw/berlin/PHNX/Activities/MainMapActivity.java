///*
// * Copyright 2013-2014 Ludwig M Brinckmann
// *
// * This program is free software: you can redistribute it and/or modify it under the
// * terms of the GNU Lesser General Public License as published by the Free Software
// * Foundation, either version 3 of the License, or (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful, but WITHOUT ANY
// * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
// * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
// *
// * You should have received a copy of the GNU Lesser General Public License along with
// * this program. If not, see <http://www.gnu.org/licenses/>.
// */
//package de.htw.berlin.PHNX.Activities;
//
//import android.graphics.drawable.Drawable;
//
//import org.mapsforge.core.graphics.Bitmap;
//import org.mapsforge.core.graphics.Canvas;
//import org.mapsforge.core.graphics.Color;
//import org.mapsforge.core.graphics.Paint;
//import org.mapsforge.core.graphics.Style;
//import org.mapsforge.core.model.BoundingBox;
//import org.mapsforge.core.model.LatLong;
//import org.mapsforge.core.model.Point;
//import org.mapsforge.core.util.MercatorProjection;
//import org.mapsforge.map.android.graphics.AndroidGraphicFactory;
//import org.mapsforge.map.android.layer.MyLocationOverlay;
//import org.mapsforge.map.layer.Layers;
//import org.mapsforge.map.layer.overlay.FixedPixelCircle;
//import org.mapsforge.map.layer.overlay.Marker;
//import org.mapsforge.map.layer.renderer.TileRendererLayer;
//
///**
// * Demonstrates how to enable a LongPress on a layer.
// *
// * In this example a long press creates/removes
// * circles, a tap on a circle toggles the colour between red and green.
// * 
// */
//public class MainMapActivity extends RenderTheme4 {
//
//	private static final Paint GREEN = Utils.createPaint(
//            AndroidGraphicFactory.INSTANCE.createColor(Color.GREEN), 0,
//            Style.FILL);
//	private static final Paint RED = Utils.createPaint(
//			AndroidGraphicFactory.INSTANCE.createColor(Color.RED), 0,
//			Style.FILL);
//	private static final Paint BLACK = Utils.createPaint(
//			AndroidGraphicFactory.INSTANCE.createColor(Color.BLACK), 0,
//			Style.FILL);
//
//	private int i;
//
//
//    private MyLocationOverlay myLocationOverlay;
//
//    @Override
//    public void onPause()
//    {
//        myLocationOverlay.disableMyLocation();
//        super.onPause();
//    }
//
//    public void onResume() {
//        super.onResume();
//        this.myLocationOverlay.enableMyLocation(true);
//    }
//
//    @Override
//    protected void createLayers()
//    {
//        super.createLayers();
//        addOverlayLayers(mapView.getLayerManager().getLayers());
//        mapView.getLayerManager().getLayers().add(this.myLocationOverlay);
//    }
//
//    protected void addOverlayLayers(Layers layers)
//    {
//        // a marker to show at the position
//        Drawable drawable = getResources().getDrawable(R.drawable.marker_red);
//        Bitmap bitmap = AndroidGraphicFactory.convertToBitmap(drawable);
//
//        // create the overlay and tell it to follow the location
//        this.myLocationOverlay = new MyLocationOverlay(this,
//                this.mapView.getModel().mapViewPosition, bitmap);
//        this.myLocationOverlay.setSnapToLocationEnabled(true);
//
//
//
//        TileRendererLayer tileRendererLayer = new TileRendererLayer(
//                this.tileCaches.get(0),
//                this.mapView.getModel().mapViewPosition,
//                false,
//                true,
//                org.mapsforge.map.android.graphics.AndroidGraphicFactory.INSTANCE) {
//            @Override
//            public boolean onLongPress(LatLong tapLatLong, Point thisXY,
//                                       Point tapXY)
//            {
//                MainMapActivity.this.onLongPress(tapLatLong);
//                return true;
//            }
//        };
//        tileRendererLayer.setMapFile(this.getMapFile());
//        tileRendererLayer.setXmlRenderTheme(this.getRenderTheme());
//        mapView.getLayerManager().getLayers().add(tileRendererLayer);
//        BLACK.setTextSize(22);
//
//
//    }
//
//	protected void onLongPress(final LatLong position)
//    {
//
//        Marker marker1 = Utils.createTappableMarker(this,
//                R.drawable.marker_green, position);
//
//		float circleSize = 20 * this.mapView.getModel().displayModel
//				.getScaleFactor();
//
//		i += 1;
//
//
//        // neuer Kreis wird erstellt
//        //
//		FixedPixelCircle tappableCircle = new FixedPixelCircle(position,
//				circleSize, GREEN, null) {
//
//			int count = i;
//
//			@Override
//			public void draw(BoundingBox boundingBox, byte zoomLevel, Canvas
//					canvas, Point topLeftPoint) {
//				super.draw(boundingBox, zoomLevel, canvas, topLeftPoint);
//
//				long mapSize = MercatorProjection.getMapSize(zoomLevel, this.displayModel.getTileSize());
//
//				int pixelX = (int) (MercatorProjection.longitudeToPixelX(position.longitude, mapSize) - topLeftPoint.x);
//				int pixelY = (int) (MercatorProjection.latitudeToPixelY(position.latitude, mapSize) - topLeftPoint.y);
//				String text = Integer.toString(count);
//				canvas.drawText(text, pixelX - BLACK.getTextWidth(text) / 2, pixelY + BLACK.getTextHeight(text) / 2, BLACK);
//			}
//
//			@Override
//			public boolean onLongPress(LatLong geoPoint, Point viewPosition,
//					Point tapPoint)
//            {
//				if (this.contains(viewPosition, tapPoint))
//                {
//                    MainMapActivity.this.mapView.getLayerManager()
//							.getLayers().remove(this);
//                    MainMapActivity.this.mapView.getLayerManager()
//							.redrawLayers();
//					return true;
//				}
//				return false;
//			}
//
//			@Override
//			public boolean onTap(LatLong geoPoint,
//                                 Point viewPosition,
//					             Point tapPoint)
//            {
//				if (this.contains(viewPosition, tapPoint))
//                {
//					toggleColor();
//					this.requestRedraw();
//					return true;
//				}
//				return false;
//			}
//
//			private void toggleColor()
//            {
//				if (this.getPaintFill().equals(MainMapActivity.GREEN))
//                {
//					this.setPaintFill(MainMapActivity.RED);
//				} else
//                {
//					this.setPaintFill(MainMapActivity.GREEN);
//				}
//			}
//		};
//		//this.mapView.getLayerManager().getLayers().add(tappableCircle);
//        this.mapView.getLayerManager().getLayers().add(marker1);
//		//tappableCircle.requestRedraw();
//
//        //Intent intent = new Intent(this,InfoText.class);
//        //startActivity(intent);
//	}
//}
