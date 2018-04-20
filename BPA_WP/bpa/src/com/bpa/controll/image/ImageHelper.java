package com.bpa.controll.image;

import java.awt.image.BufferedImage;

public class ImageHelper {
	public static final int WHITE = 0x00FFFFFF, BLACK = 0x00000000;
	
	public static BufferedImage doImageTreatment(BufferedImage _image) {
		clenaImage(_image);
		_image = justifyImage(_image);
		return _image;
	}

	private static BufferedImage justifyImage(BufferedImage _image) {
		int range = _image.getWidth()/4;
		int interval;
		int start=0;
		int intervalEnd;
		int intervalAverageHeight;
		int desiredHeight = 5;
		int pixel;
		
		BufferedImage newImage = createBlankImage(_image.getWidth(), _image.getHeight(), _image.getType());
		for(interval=1; interval < 5; interval++, start=intervalEnd+1) {
			intervalEnd = (range*interval);
			intervalAverageHeight = getIntervalMediumHeight(_image, start, intervalEnd);
			for (int intervalWidth=start; intervalWidth<=intervalEnd; intervalWidth++) {
				for(int intervalHeight=intervalAverageHeight, count=desiredHeight; intervalHeight<_image.getHeight(); intervalHeight++, count++) {
					if(intervalHeight >= _image.getHeight() || count >= _image.getHeight())
						break;
					pixel = _image.getRGB(intervalWidth, intervalHeight);
					newImage.setRGB(intervalWidth, count, pixel);
				}
			}
		}	
		return newImage;
	}
	private static int getIntervalMediumHeight(BufferedImage _image, int start, int end) {
		int mediumHeight = 0;
		int height = _image.getHeight();
		int pixel;
		int count = 0;
		for(int x=start; x<end; x++) {
			for(int y=0; y<height;y++) {
				pixel = _image.getRGB(x, y);
				if(pixel != -1) {
					mediumHeight += y;
					break;
				}			
					
			}
			count++;
		}
		return count>0?mediumHeight/count:0;
	}

	public static void clenaImage(BufferedImage _image) {
		int average = 0;
		/*
		// set an average background colour
		for( int row = 0; ++row < _image.getHeight(); )
			for ( int column = 0; ++column < _image.getWidth(); )
				average += _image.getRGB(column, row) & 0x000000FF;
		average /= _image.getWidth() * _image.getHeight(); // (largura * altura) / media
		*/
		average = 155;

		// set image colours to black and white
		for( int row = 0; ++row < _image.getHeight(); )
			for ( int column = 0; ++column < _image.getWidth(); )
				if ((_image.getRGB(column, row) & 0x000000FF) <= average * .80)
					_image.setRGB(column, row, BLACK);
				else
					_image.setRGB(column, row, WHITE);

		for( int row = 0; ++row < _image.getHeight(); )
			for ( int column = 0; ++column < _image.getWidth(); )
				if ((_image.getRGB(column, row) & WHITE) == WHITE) {
					int height = countVerticalWhite(_image, column, row);
					int width = countHorizontalWhite(_image, column, row);
					if ((width * height <= 6) || (width == 1) || (height == 1))
						_image.setRGB(column, row, BLACK);
				}
		
		for( int row = 0; ++row < _image.getHeight(); )
			for ( int column = 0; ++column < _image.getWidth(); )
				if ((_image.getRGB(column, row) & WHITE) != WHITE)
					if (countBlackNeighbors(_image, column, row) <= 3)
						_image.setRGB(column, row, WHITE);
		
		for (int column = 0; column < _image.getWidth(); column++) 
			_image.setRGB(column, 0, WHITE);
		for(int row = 1; row < _image.getHeight(); row++) 
			_image.setRGB(0, row, WHITE);
		for(int x=0; x<_image.getWidth(); x++) {
			for(int y=0; y<_image.getHeight(); y++) {
				if(_image.getRGB(x, y) != -1 && !hasNeighbours(x, y, _image))// se nao tiver visinhos
					_image.setRGB(x,y,-1);
			}
		}
	}

	private static boolean hasNeighbours(int x, int y, BufferedImage _image) {
		int width = _image.getWidth();
		int height = _image.getHeight();
		
		if(x<width-2) {
			if(_image.getRGB(x+1, y)!=-1 && _image.getRGB(x+2, y)!=-1)
				return true;
		}
		if(y<height-2) {
			if(_image.getRGB(x, y+1)!=-1 && _image.getRGB(x, y+2)!=-1)
				return true;
		}
			
		return false;
	}

	private static int countVerticalWhite(BufferedImage image, int x, int y) {
		return (countAboveWhite(image, x, y) + countBelowWhite(image, x, y)) + 1;
	}
	
	private static int countHorizontalWhite(BufferedImage image, int x, int y) {
		return (countLeftWhite(image, x, y) + countRightWhite(image, x, y)) + 1;
	}
	
	private static int countAboveWhite(BufferedImage image, int x, int y) { 
		int leftWhite = 0; 
		y--; 
		while (y-- > 0) 
			if ((image.getRGB(x, y) & WHITE) == WHITE) 
				leftWhite++; 
			else 
				break; 
		return leftWhite; 
	}
	
	private static int countBelowWhite(BufferedImage image, int x, int y) { 
		int rightWhite = 0; 
		y++; 
		while (y < image.getHeight()) 
			if ((image.getRGB(x, y++) & WHITE) == WHITE) 
				rightWhite++; 
			else 
				break; 
		return rightWhite; 
	} 
	
	private static int countLeftWhite(BufferedImage image, int x, int y) {
		int leftWhite = 0;
		x--;
		while (x-- > 0)
			if ((image.getRGB(x, y) & WHITE) == WHITE)
				leftWhite++;
			else
				break;
		return leftWhite;
	}
	
	private static int countRightWhite(BufferedImage image, int x, int y) {
		int rightWhite = 0;
		x++;
		while (x < image.getWidth())
			if ((image.getRGB(x++, y) & WHITE) == WHITE)
				rightWhite++;
			else
				break;
		return rightWhite;
	}
	
	private static int countBlackNeighbors(BufferedImage image, int x, int y) {
		int numBlacks = 0;
		if (pixelColor(image, x - 1, y) != WHITE)
			numBlacks++;
		if (pixelColor(image, x - 1, y + 1) != WHITE)
			numBlacks++;
		if (pixelColor(image, x - 1, y - 1) != WHITE)
			numBlacks++;
		if (pixelColor(image, x, y + 1) != WHITE)
			numBlacks++;
		if (pixelColor(image, x, y - 1) != WHITE)
			numBlacks++;
		if (pixelColor(image, x + 1, y) != WHITE)
			numBlacks++;
		if (pixelColor(image, x + 1, y + 1) != WHITE)
			numBlacks++;
		if (pixelColor(image, x + 1, y - 1) != WHITE)
			numBlacks++;
		return numBlacks;
	}
	
	private static int pixelColor(BufferedImage image, int x, int y) {
		if (x >= image.getWidth() || x < 0 || y < 0 || y >= image.getHeight())
			return WHITE;
		return image.getRGB(x, y) & WHITE;
	}
	
	private static BufferedImage createBlankImage(int _width, int _height, int _type) {
		BufferedImage newImage = new BufferedImage(_width, _height, _type);
		for(int x=0; x<_width; x++) {
			for(int y=0; y<_height; y++) {
				newImage.setRGB(x, y, -1);
			}
		}
		return newImage;
	}
	
	/*
	private static List<BufferedImage> breakImage(BufferedImage _image) {
		int width = _image.getWidth();
		int range = width/4;
		int interval;
		int height = _image.getHeight();
		
		int start=0;
		int end;
		int pixel;

		List<BufferedImage> imageList = new ArrayList<BufferedImage>();
		BufferedImage newImage = null;
		for(interval=1; interval < 5; interval++, start=end) {
			end = (range*interval);
			newImage = new BufferedImage(range, height, _image.getType());
			for(int x=start, count=0; x<end; x++, count++) {
				for(int y=0; y<height; y++) {
					pixel = _image.getRGB(x, y);
					newImage.setRGB(count, y, pixel);
				}
			}
			imageList.add(newImage);
		}	
		return imageList;
	}
	*/
}
