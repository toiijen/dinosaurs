
public static class Modelbrachiosaurus extends EntityModel {
	private final RendererModel rightlegs;
	private final RendererModel leftlegs;
	private final RendererModel tail;
	private final RendererModel cube_r1;
	private final RendererModel tail2;
	private final RendererModel tail3_r1;
	private final RendererModel cube_r2;
	private final RendererModel headandneck;
	private final RendererModel cube_r3;
	private final RendererModel cube_r4;
	private final RendererModel cube_r5;
	private final RendererModel cube_r6;
	private final RendererModel cube_r7;
	private final RendererModel bb_main;
	private final RendererModel cube_r8;
	private final RendererModel cube_r9;
	private final RendererModel cube_r10;

	public Modelbrachiosaurus() {
		textureWidth = 512;
		textureHeight = 512;

		rightlegs = new RendererModel(this);
		rightlegs.setRotationPoint(31.0F, 24.0F, 24.0F);
		rightlegs.cubeList.add(new ModelBox(rightlegs, 178, 274, -62.0F, -74.0F, -12.0F, 12, 74, 13, 0.0F, false));
		rightlegs.cubeList.add(new ModelBox(rightlegs, 50, 292, -12.0F, -61.0F, -12.0F, 12, 61, 13, 0.0F, false));

		leftlegs = new RendererModel(this);
		leftlegs.setRotationPoint(31.0F, 24.0F, -13.0F);
		leftlegs.cubeList.add(new ModelBox(leftlegs, 128, 274, -62.0F, -74.0F, -12.0F, 12, 74, 13, 0.0F, false));
		leftlegs.cubeList.add(new ModelBox(leftlegs, 0, 292, -12.0F, -61.0F, -12.0F, 12, 61, 13, 0.0F, false));

		tail = new RendererModel(this);
		tail.setRotationPoint(47.0F, -43.8333F, 1.1667F);

		cube_r1 = new RendererModel(this);
		cube_r1.setRotationPoint(1.0F, 3.8333F, 3.8333F);
		tail.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, 0.2182F);
		cube_r1.cubeList.add(new ModelBox(cube_r1, 0, 179, -1.0F, -15.0F, -25.0F, 48, 25, 41, 0.0F, false));

		tail2 = new RendererModel(this);
		tail2.setRotationPoint(46.0F, 7.8333F, 0.3333F);
		tail.addChild(tail2);

		tail3_r1 = new RendererModel(this);
		tail3_r1.setRotationPoint(29.8554F, 11.414F, 2.6157F);
		tail2.addChild(tail3_r1);
		setRotationAngle(tail3_r1, 0.0F, -0.3054F, 0.0873F);
		tail3_r1.cubeList.add(new ModelBox(tail3_r1, 128, 248, -1.0F, -4.5F, -8.5F, 44, 9, 17, 0.0F, false));

		cube_r2 = new RendererModel(this);
		cube_r2.setRotationPoint(-9.0F, 4.0F, 1.5F);
		tail2.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -0.1745F, 0.2182F);
		cube_r2.cubeList.add(new ModelBox(cube_r2, 232, 0, -1.0F, -11.0F, -16.0F, 48, 15, 23, 0.0F, false));

		headandneck = new RendererModel(this);
		headandneck.setRotationPoint(-30.5F, -66.0F, -1.05F);

		cube_r3 = new RendererModel(this);
		cube_r3.setRotationPoint(-85.5F, -73.0F, -1.7F);
		headandneck.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 0.2618F);
		cube_r3.cubeList.add(new ModelBox(cube_r3, 0, 0, -10.0F, -9.0F, 0.0F, 10, 11, 4, 0.0F, false));

		cube_r4 = new RendererModel(this);
		cube_r4.setRotationPoint(-102.5F, -46.5F, 19.05F);
		headandneck.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.2182F);
		cube_r4.cubeList.add(new ModelBox(cube_r4, 250, 250, -9.0F, -30.0F, -29.0F, 29, 22, 21, 0.0F, false));

		cube_r5 = new RendererModel(this);
		cube_r5.setRotationPoint(-81.5F, -36.5F, 19.05F);
		headandneck.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, 0.7854F);
		cube_r5.cubeList.add(new ModelBox(cube_r5, 0, 245, -16.0F, -20.0F, -33.0F, 36, 19, 28, 0.0F, false));

		cube_r6 = new RendererModel(this);
		cube_r6.setRotationPoint(-47.5F, -12.0F, 19.05F);
		headandneck.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.6545F);
		cube_r6.cubeList.add(new ModelBox(cube_r6, 229, 99, -23.0F, -20.0F, -36.0F, 43, 24, 33, 0.0F, false));

		cube_r7 = new RendererModel(this);
		cube_r7.setRotationPoint(-23.5F, 4.0F, 19.05F);
		headandneck.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, 0.48F);
		cube_r7.cubeList.add(new ModelBox(cube_r7, 178, 179, -21.0F, -24.0F, -40.0F, 41, 28, 41, 0.0F, false));

		bb_main = new RendererModel(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);

		cube_r8 = new RendererModel(this);
		cube_r8.setRotationPoint(23.0F, -91.25F, 17.0F);
		bb_main.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, 0.2618F);
		cube_r8.cubeList.add(new ModelBox(cube_r8, 0, 85, -56.0F, -1.0F, -40.0F, 85, 1, 46, 0.0F, false));

		cube_r9 = new RendererModel(this);
		cube_r9.setRotationPoint(14.0F, -56.5F, 17.0F);
		bb_main.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, 0.0F, 0.2618F);
		cube_r9.cubeList.add(new ModelBox(cube_r9, 0, 132, -56.0F, -1.0F, -40.0F, 85, 1, 46, 0.0F, false));

		cube_r10 = new RendererModel(this);
		cube_r10.setRotationPoint(45.0F, -49.0F, 24.0F);
		bb_main.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, 0.2618F);
		cube_r10.cubeList.add(new ModelBox(cube_r10, 0, 0, -91.0F, -35.0F, -49.0F, 91, 35, 50, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		rightlegs.render(f5);
		leftlegs.render(f5);
		tail.render(f5);
		headandneck.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(RendererModel modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}